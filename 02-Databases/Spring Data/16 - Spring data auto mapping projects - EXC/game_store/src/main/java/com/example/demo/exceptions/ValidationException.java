package com.example.demo.exceptions;

public class ValidationException extends RuntimeException {

    public ValidationException(String reason) {
        super(reason);
    }
}
