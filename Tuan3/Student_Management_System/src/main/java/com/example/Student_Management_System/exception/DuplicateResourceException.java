package com.example.Student_Management_System.exception;

public class DuplicateResourceException extends AppException {
    public DuplicateResourceException(String resource, String field, Object value) {
        super(String.format("%s đã tồn tại với %s: '%s'", resource, field, value), 409);
    }
}
