package com.frolaan.movies;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class FavoriteMoviesViewModel extends AndroidViewModel {

    private final MovieDao movieDao;
    public FavoriteMoviesViewModel(@NonNull Application application) {
        super(application);
        movieDao = MovieDatabase.getInstance(application).movieDao();
    }

    public LiveData<List<Movie>> getFavoriteMovies() {
        return movieDao.getAllFavouriteMovies();
    }
}
