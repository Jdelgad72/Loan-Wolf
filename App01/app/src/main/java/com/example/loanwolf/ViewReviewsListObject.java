package com.example.loanwolf;

public class ViewReviewsListObject {
    private String name;
    private String rating;
    private String comment;

    public ViewReviewsListObject(String name, String rating, String comment) {
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
