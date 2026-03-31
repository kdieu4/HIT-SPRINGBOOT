package com.example.Student_Management_System.exception;

public class BadRequestException extends AppException {
    public BadRequestException(String message) {
        super(message, 400);
    }
}
