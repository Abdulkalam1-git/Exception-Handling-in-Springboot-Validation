package com.telusko.demo6.Exception;

public class ErrorHandler extends RuntimeException {

    private String message;

    public ErrorHandler(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
