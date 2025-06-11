package org.example.service.serviceimpl;

import org.example.service.Constraint;

public class IntegerConstraint implements Constraint {
    @Override
    public boolean isValid(Object value) {
        if (value == null) return true;
        return value instanceof Integer && -1024 <= (Integer) value && 1024 >= (Integer) value;
    }

    @Override
    public String getErrorMessage() {
        return "Integer constraint violated! Integer must lie between -1024 to 1024";
    }
}
