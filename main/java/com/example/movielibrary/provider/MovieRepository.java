package com.example.movielibrary.provider;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class MovieRepository {
    private LiveData<List<MovieDB>> allMovies;
    private MovieDao movieDao;

    MovieRepository(Application application){
        MovieDatabase db = MovieDatabase.getDatabase(application);
        movieDao = db.movieDao();
        allMovies = movieDao.getAllMovies();
    }

    public LiveData<List<MovieDB>> getAllMovies() {
        return allMovies;
    }

    void insert(MovieDB movieDB){
        MovieDatabase.databaseWriteExecutor.execute(()->movieDao.addMovie(movieDB));
    }

    void deleteAll(){
        MovieDatabase.databaseWriteExecutor.execute(()->movieDao.deleteAllMovies());
    }

    void deleteMovie(String inputMovie){
        MovieDatabase.databaseWriteExecutor.execute(()->movieDao.deleteMovie(inputMovie));
    }

    void deleteMovieOlderThan2010(){
        MovieDatabase.databaseWriteExecutor.execute(()->movieDao.deleteMovieOlderThan2010());

    }

    void deleteMovieCostAbove20(){
        MovieDatabase.databaseWriteExecutor.execute(()->movieDao.deleteMovieCostAbove20());
    }
}
