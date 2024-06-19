package com.example.movielibrary.provider;

import static com.example.movielibrary.provider.MovieDB.TABLE_NAME;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = TABLE_NAME)
public class MovieDB {
    public static final String TABLE_NAME = "movies";

    @PrimaryKey (autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "movie_id")
    private int id;
    @ColumnInfo(name = "movie_title")
    private String title;
    @ColumnInfo(name = "movie_year")
    private int year;
    @ColumnInfo(name = "movie_country")
    private String country;
    @ColumnInfo(name = "movie_genre")
    private String genre;
    @ColumnInfo(name = "movie_cost")
    private int cost;
    @ColumnInfo(name = "movie_keyword")
    private String keyword;
    @ColumnInfo(name = "movie_rating")
    private int rating;

    public MovieDB(String title, int year, String country, String genre, int cost, String keyword, int rating) {
        this.title = title;
        this.year = year;
        this.country = country;
        this.genre = genre;
        this.cost = cost;
        this.keyword = keyword;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public String getCountry() {
        return country;
    }

    public String getGenre() {
        return genre;
    }

    public int getCost() {
        return cost;
    }

    public String getKeyword() {
        return keyword;
    }

    public int getRating() {
        return rating;
    }

    public void setId(int id) {
        this.id = id;
    }
}
