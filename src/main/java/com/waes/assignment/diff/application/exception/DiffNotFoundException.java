package com.waes.assignment.diff.application.exception;

public class DiffNotFoundException extends RuntimeException{
    public DiffNotFoundException() {
    }

    public DiffNotFoundException(String message) {
        super(message);
    }
}
