package org.example.entities;

import org.example.service.Constraint;

import java.util.List;

public class Column {
    String name;
    ColumnType type;
    List<Constraint> constraints;

    public Column(String name, ColumnType type, List<Constraint> constraints) {
        this.name = name;
        this.type = type;
        this.constraints = constraints;
    }

    public boolean validate(Object value) {
        for (Constraint constraint : constraints) {
            if (!constraint.isValid(value)) {
                throw new IllegalArgumentException(constraint.getErrorMessage());
            }
        }
        return true;
    }
}
