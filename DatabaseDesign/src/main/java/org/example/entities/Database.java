package org.example.entities;

import org.example.manager.TableManager;

public class Database {
    String name;
    TableManager tableManager;

    public Database(String name) {
        this.name = name;
        this.tableManager = new TableManager();
    }

    public String getName() {
        return name;
    }

    public TableManager getTableManager() {
        return tableManager;
    }
}
