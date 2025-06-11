package org.example.manager;

import org.example.entities.Column;
import org.example.entities.Table;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TableManager {
    Map<String, Table> tableMap;

    public TableManager() {
        tableMap = new HashMap<>();
    }

    public void createTable(String name, List<Column> columns) {
        if (tableMap.containsKey(name)) {
            throw new IllegalArgumentException("Table: " + name + " already exist!");
        }
        Table t = new Table(name, columns);
        tableMap.put(name, t);
    }

    public void truncateTable(String name) {
        if (!tableMap.containsKey(name)) {
            throw new IllegalArgumentException("No table: " + name + " exists!");
        }

        Table t = tableMap.get(name);
        t.deleteRecords();
        tableMap.put(name, t);
    }

    public void deleteTable(String name) {
        if (!tableMap.containsKey(name)) {
            throw new IllegalArgumentException("No table: " + name + " exists!");
        }
        tableMap.remove(name);
    }

    public Table getTable(String name) {
        if (!tableMap.containsKey(name)) {
            throw new IllegalArgumentException("Table: " + name + " doesn't exists!");
        }
        return tableMap.get(name);
    }

}
