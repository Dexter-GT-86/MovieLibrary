package com.example.movielibrary;

public class Movie {
    private String title;
    private String year;
    private String country;
    private String genre;
    private String cost;
    private String keyword;
    private String rating;
    //private String new_cost;

    public Movie(String title, String year, String country, String genre, String cost, String keyword, String rating) {
        this.title = title;
        this.year = year;
        this.country = country;
        this.genre = genre;
        this.cost = cost;
        this.keyword = keyword;
        this.rating = rating;
        //this.new_cost = Integer.parseInt(cost)*0.8+"";
    }

    public String getTitle() {
        return title;
    }

    public String getYear() {
        return year;
    }

    public String getCountry() {
        return country;
    }

    public String getGenre() {
        return genre;
    }

    public String getCost() {
        return cost;
    }

    public String getKeyword() {
        return keyword;
    }

    public String getRating() {
        return rating;
    }

    //public String getNew_cost(){return  new_cost;}
}
