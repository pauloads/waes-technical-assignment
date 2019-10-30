package com.waes.assignment.diff.application.handler;

public class ApiError {

    private String cause;
    private String uri;
    private String timestamp;
    private int status;

    public String getCause() {
        return cause;
    }

    public ApiError withCause(String cause) {
        this.cause = cause;
        return this;
    }

    public String getUri() {
        return uri;
    }

    public ApiError withUri(String uri) {
        this.uri = uri;
        return this;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public ApiError withTimestamp(String timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public int getStatus() {
        return status;
    }

    public ApiError withStatus(int status) {
        this.status = status;
        return this;
    }
}
