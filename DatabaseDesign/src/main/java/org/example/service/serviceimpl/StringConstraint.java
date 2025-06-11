package org.example.service.serviceimpl;

import org.example.service.Constraint;

public class StringConstraint implements Constraint {
    @Override
    public boolean isValid(Object value) {
        return value instanceof String && ((String) value).length() <= 20;
    }

    @Override
    public String getErrorMessage() {
        return "String constraint violated! The length should be less than 20";
    }
}
