package com.example.reactivewebaap.api;

public class ResponseWrapper {

    private String message;

    public ResponseWrapper(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
