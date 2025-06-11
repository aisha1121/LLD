package org.example.service.serviceimpl;

import org.example.service.Constraint;

public class RequiredConstraint implements Constraint {
    @Override
    public boolean isValid(Object value) {
        return value != null;
    }

    @Override
    public String getErrorMessage() {
        return "Required field!";
    }
}
