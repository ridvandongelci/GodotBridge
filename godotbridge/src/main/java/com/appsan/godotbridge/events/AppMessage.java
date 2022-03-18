package com.appsan.godotbridge.events;

public class AppMessage {
    private String message;

    public AppMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
