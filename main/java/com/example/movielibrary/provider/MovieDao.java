package com.example.movielibrary.provider;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MovieDao {
    @Query("select * from movies")
    LiveData<List<MovieDB>> getAllMovies();

    @Query("select * from movies where movie_title=:name")
    List<MovieDB> getMovie(String name);

    @Insert
    void addMovie(MovieDB movieDb);

    @Query("delete from movies where movie_title=:name")
    void deleteMovie(String name);

    @Query("delete FROM movies")
    void deleteAllMovies();

    @Query("delete  from movies where movie_year < 2010")
    void deleteMovieOlderThan2010();

    @Query("delete  from movies where movie_cost > 20")
    void deleteMovieCostAbove20();



}
