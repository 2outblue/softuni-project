package com.ngfrt.appmain.service.exception;

public class EventServiceException extends RuntimeException{

    private final int statusCode;
    public EventServiceException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
