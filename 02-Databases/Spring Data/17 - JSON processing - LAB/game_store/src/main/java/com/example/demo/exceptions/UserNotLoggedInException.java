package com.example.demo.exceptions;

public class UserNotLoggedInException extends RuntimeException {

    public UserNotLoggedInException() {
        super("Execute login command first!");
    }
}
