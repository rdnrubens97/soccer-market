package com.home.soccermarket.exception;

public class CustomException extends Exception {
    public CustomException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "CustomException: " + getMessage();
    }
}
