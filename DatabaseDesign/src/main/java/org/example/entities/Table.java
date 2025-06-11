package org.example.entities;

import java.util.*;

public class Table {
    String name;
    List<Column> columns;
    // columnName -> value
    List<Map<String, Object>> records;
    Map<String, Index> compositeIndexes;


    public Table(String name, List<Column> columns) {
        this.name = name;
        this.columns = columns;
        this.records = new ArrayList<>();
        this.compositeIndexes = new HashMap<>();
    }

    public void createIndex(List<String> columnNames) {
        String key = String.join(",", columnNames);
        if (compositeIndexes.containsKey(key)) {
            throw new IllegalArgumentException("Index already exists on columns: " + key);
        }
        Index index = new Index(columnNames);
        for (Map<String, Object> record : records) {
            index.add(record);
        }
        compositeIndexes.put(key, index);
    }

    public void deleteIndex(List<String> columnNames) {
        String key = String.join(",", columnNames);
        if (!compositeIndexes.containsKey(key)) {
            throw new IllegalArgumentException("No index exists on columns: " + key);
        }
        compositeIndexes.remove(key);
    }


    public void addRow(Map<String, Object> rows) {
        // loop through the columns as constraints need to be validated
        // if key not present -> add null
        // verify each column name
        // if any constraint violation -> throw exception
        for (Column column : columns) {
            if (!rows.containsKey(column.name)) {
                rows.put(column.name, null);
            }
            column.validate(rows.get(column.name));
        }
        records.add(rows);
        for (Index index : compositeIndexes.values()) {
            index.add(rows);
        }
    }

    public void filterRecords(List<String> columnNames, List<Object> values) {
        String key = String.join(",", columnNames);
        if (compositeIndexes.containsKey(key)) {
            List<Map<String, Object>> results = compositeIndexes.get(key).search(values);
            for (Map<String, Object> record : results) {
                System.out.println(record);
            }
        } else {
            for (Map<String, Object> record : records) {
                boolean match = true;
                for (int i = 0; i < columnNames.size(); i++) {
                    if (!Objects.equals(record.get(columnNames.get(i)), values.get(i))) {
                        match = false;
                        break;
                    }
                }
                if (match) {
                    System.out.println(record);
                }
            }
        }
    }


    public void deleteRecords() {
        records.clear();
        for (Index index : compositeIndexes.values()) {
            index.clear();
        }
    }

    public void print() {
        for (Map<String, Object> record : records) {
            System.out.println(record);
        }
    }

    public void update(String colName, Object oldValue, Object newValue) {
        boolean matchFound = false;
        for (Map<String, Object> record : records) {
            if (record.containsKey(colName) && record.get(colName).equals(oldValue)) {
                matchFound = true;
                Column column = getColumn(colName);
                if (column != null && column.validate(newValue)) {
                    record.put(colName, newValue);
                }
            }
        }

        if (!matchFound) {
            throw new IllegalArgumentException("No such record found to update!");
        }
    }

    private Column getColumn(String colName) {
        for (Column column : columns) {
            if (column.name.equals(colName)) {
                return column;
            }
        }
        return null;
    }
}
