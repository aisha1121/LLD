package org.example.service;

public interface Constraint {
    boolean isValid(Object value);
    String getErrorMessage();
}
