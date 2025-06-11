package org.example;

import org.example.entities.Column;
import org.example.entities.ColumnType;
import org.example.entities.Database;
import org.example.entities.Table;
import org.example.manager.DatabaseManager;
import org.example.manager.TableManager;
import org.example.service.Constraint;
import org.example.service.serviceimpl.IntegerConstraint;
import org.example.service.serviceimpl.RequiredConstraint;
import org.example.service.serviceimpl.StringConstraint;

import java.util.*;

public class DatabaseDemo {
    public static void main(String[] args) {
        try {
            DatabaseManager databaseManager = DatabaseManager.getInstance();
            databaseManager.createDatabase("DB1");
            Database database = databaseManager.getDatabase("DB1");
            if (database == null) {
                System.out.println("No such database exists");
            } else {
                List<Constraint> nameConstraints = Arrays.asList(new StringConstraint(), new RequiredConstraint());
                List<Constraint> ageConstraints = Arrays.asList(new IntegerConstraint());

                List<Column> columns = Arrays.asList(new Column("name", ColumnType.STRING, nameConstraints), new Column("age", ColumnType.INT, ageConstraints));
                TableManager tableManager = database.getTableManager();
                tableManager.createTable("Users", columns);
                Table table = tableManager.getTable("Users");
                if (table == null) {
                    System.out.println("No table found");
                } else {
                    table.createIndex(Arrays.asList("name"));
                    Map<String, Object> record1 = Map.of(
                            "name", "Aayusha",
                            "age" , 25
                    );
                    // note: this is immutable map declaration so it cant be updated afterwards
//                    Map<String, Object> record2 = Map.of(
//                            "name", "Ankit"
//                    );
                    Map<String, Object> record2 = new HashMap<>();
                    record2.put("name" , "Ankit");
                    Map<String, Object> record3 = Map.of(
                            "name", "Divya",
                            "age" , 2530
                    );
                    table.addRow(record1);
                    table.print();
                    table.addRow(record2);
                    table.print();
//                    table.addRow(record3); // exception
//                    table.print();

                    table.update("name", "Ankit", "Ankit Srivastava");
                    table.print();

                    tableManager.truncateTable("Users");
                    table.print();

                    tableManager.deleteTable("Users");
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Error occurred: " + e.getMessage());
        }

    }
}
