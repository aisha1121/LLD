package org.example.entities;

import java.util.*;

class Index {
    List<String> columnNames;
    Map<List<Object>, List<Map<String, Object>>> indexMap;

    Index(List<String> columnNames) {
        this.columnNames = columnNames;
        this.indexMap = new HashMap<>();
    }

    private List<Object> getKey(Map<String, Object> record) {
        List<Object> key = new ArrayList<>();
        for (String col : columnNames) {
            key.add(record.get(col));
        }
        return key;
    }

    public void add(Map<String, Object> record) {
        List<Object> key = getKey(record);
        indexMap.computeIfAbsent(key, k -> new ArrayList<>()).add(record);
    }

    public void remove(Map<String, Object> record) {
        List<Object> key = getKey(record);
        List<Map<String, Object>> list = indexMap.get(key);
        if (list != null) {
            list.remove(record);
            if (list.isEmpty()) {
                indexMap.remove(key);
            }
        }
    }

    public List<Map<String, Object>> search(List<Object> values) {
        return indexMap.getOrDefault(values, new ArrayList<>());
    }

    public void clear() {
        indexMap.clear();
    }
}
