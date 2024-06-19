package com.example.movielibrary.provider;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {MovieDB.class}, version = 1)
public abstract class MovieDatabase extends RoomDatabase {
    public abstract MovieDao movieDao();
    public static final String MOVIE_DATABASE_NAME = "movie_database";

    private static volatile MovieDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;

    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static MovieDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (MovieDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MovieDatabase.class, MOVIE_DATABASE_NAME)
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
