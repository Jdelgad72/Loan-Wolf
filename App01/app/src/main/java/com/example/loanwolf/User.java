package com.example.loanwolf;

//Creates a user class so that it can store the necessary info id, name, email, and gender
public class User {
    private int id;
    private String email, firstName, lastName;

    public User(int id, String email, String firstName, String lastName) {
        this.id = id;
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

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
