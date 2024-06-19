package com.example.movielibrary.provider;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class MovieViewModel extends AndroidViewModel {

    private MovieRepository repository;
    private LiveData<List<MovieDB>> allMovies;

    public MovieViewModel(@NonNull Application application) {
        super(application);
        repository = new MovieRepository(application);
        allMovies = repository.getAllMovies();
    }

    public LiveData<List<MovieDB>> getAllMovies() {
        return allMovies;
    }

    public void insert(MovieDB movieDB){
        repository.insert(movieDB);
    }

    public void deleteAll(){
        repository.deleteAll();
    }

    public void deleteMovie(String inputMovie){
        repository.deleteMovie(inputMovie);
    }

    public void deleteMovieOlderThan2010(){
        repository.deleteMovieOlderThan2010();
    }

    public void deleteMovieCostAbove20(){
        repository.deleteMovieCostAbove20();
    }

}
