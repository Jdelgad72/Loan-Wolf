package com.example.loanwolf;

public class LeaderboardListObject {
    private String rank;
    private String name;
    private String email;
    private String starRating;

    public LeaderboardListObject(String rank, String name, String email, String starRating) {
        this.rank = rank;
        this.name = name;
        this.email = email;
        this.starRating = starRating;
    }

    public String getrank() {
        return this.rank;
    }

    public String getname() {
        return this.name;
    }

    public String getemail() {
        return this.email;
    }

    public String getstarRating() {
        return this.starRating;
    }

}