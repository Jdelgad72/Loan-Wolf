package com.example.loanwolf;

public class UserNames {
    private String userName;
    private String email;

    public UserNames(String userName, String email) {
        this.userName = userName;
        this.email = email;
    }

    public String getUserName() {
        return this.userName;
    }

    public String getEmail() {
        return this.email;
    }
}
