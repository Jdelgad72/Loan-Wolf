package com.example.loanwolf;

public class NewReviewsListObject {
    private String name;
    private String rating;
    private String comment;

    public NewReviewsListObject(String name, String rating, String comment) {
        this.name = name;
        this.rating = rating;
        this.comment = comment;
    }

    public String getname() {
        return this.name;
    }

    public String getrating() {
        return this.rating;
    }

    public String getcomment() {
        return this.comment;
    }
}
