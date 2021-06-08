package com.sachet.departmentservice.custom_error;

public class DepartmentNotFoundException extends Exception {

    public DepartmentNotFoundException(String message) {
        super(message);
    }
}
