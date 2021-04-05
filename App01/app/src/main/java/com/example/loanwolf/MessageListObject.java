package com.example.loanwolf;

public class MessageListObject {
    private String name;
    private String email;
    private String message;
    private String status;
    private String date;
    private String time;

    public MessageListObject(String name, String email, String message, String status, String date, String time){
        this.name = name;
        this.email = email;
        this.message = message;
        this.status = status;
        this.date = date;
        this.time = time;
    }

    public String getName() {
        return this.name;
    }

    public String getEmail() {
        return this.email;
    }

    public String getMessage() {
        return this.message;
    }

    public String getStatus() {
        return this.status;
    }

    public String getDate() {
        return this.date;
    }

    public String getTime() {
        return this.time;
    }

}
