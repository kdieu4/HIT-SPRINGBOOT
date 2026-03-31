package com.example.Student_Management_System.exception;

public class AppException extends RuntimeException {
    private int errorCode;

    public AppException(String message, int errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
