package com.hit.LibrarySystem.exception.extended;

public class BadRequestException extends AppException {
    public BadRequestException(String message) {
        super(400, message);
    }
}