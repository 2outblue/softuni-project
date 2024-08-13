package com.ngfrt.appmain.service.exception;

public class EventNotFoundException extends EventServiceException{


    public EventNotFoundException(String message) {
        super(message, 404);
    }
}
