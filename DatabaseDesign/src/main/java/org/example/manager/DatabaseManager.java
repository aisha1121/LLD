package org.example.manager;

import org.example.entities.Database;

import java.util.HashMap;
import java.util.Map;

public class DatabaseManager {
    private volatile static DatabaseManager instance;
    private final Map<String, Database> databaseMap;

    private DatabaseManager() {
        databaseMap = new HashMap<>();
    }

    public static DatabaseManager getInstance() {
        if (instance == null) {
            synchronized (DatabaseManager.class) {
                if (instance == null) {
                    instance = new DatabaseManager();
                }
            }
        }
        return instance;
    }


    public void createDatabase(String dbName) {
        if (databaseMap.containsKey(dbName)) {
            throw new IllegalArgumentException("Database: " + dbName + " already exists!");
        }
        databaseMap.put(dbName, new Database(dbName));
    }

    public void deleteDatabase(String dbName) {
        if (!databaseMap.containsKey(dbName)) {
            throw new IllegalArgumentException("Database: " + dbName + " doesn't exists!");
        }
        databaseMap.remove(dbName);
    }

    public Database getDatabase(String name) {
        return databaseMap.getOrDefault(name, null);
    }
}
