package com.example.loanwolf;

public class NotificationListObject {
    private String message;
    private String type;

    public NotificationListObject(String message, String type) {
        this.message = message;
        this.type = type;
    }

    public String getMessage() {
        return this.message;
    }

    public String getType() {
        return this.type;
    }
}
