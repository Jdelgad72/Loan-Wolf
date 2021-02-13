package com.example.loanwolf;

//Creates a user class so that it can store the necessary info id, name, email, and gender
public class User {
    private int id;
    private String username, email, firstName, lastName, googleId;

    public User(int id, String email, String username, String firstName, String lastName, String googleId) {
        this.id = id;
        this.googleId = googleId;
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getGoogleID() {
        return googleId;
    }
}
