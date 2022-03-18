package com.appsan.godotbridge.events;

public class GodotMessage {
    private String message;

    public GodotMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
