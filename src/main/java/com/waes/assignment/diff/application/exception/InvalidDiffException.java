package com.waes.assignment.diff.application.exception;

public class InvalidDiffException extends RuntimeException {
    public InvalidDiffException() {
    }

    public InvalidDiffException(String message) {
        super(message);
    }
}
