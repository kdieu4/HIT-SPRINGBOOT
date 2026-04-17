package com.hit.LibrarySystem.exception.extended;

public class DuplicateResourceException extends AppException {
    public DuplicateResourceException(String resourceName, String fieldName, Object fieldValue) {
        super(409, String.format("%s da ton tai voi %s: '%s'", resourceName, fieldName, fieldValue));
    }
}
