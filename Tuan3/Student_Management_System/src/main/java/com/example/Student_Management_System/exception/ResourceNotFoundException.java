package com.example.Student_Management_System.exception;

public class ResourceNotFoundException extends AppException {
    public ResourceNotFoundException(String resource, String field, Object value) {
        super(String.format("%s không tìm thấy với %s: '%s'", resource, field, value), 404);
    }
}
