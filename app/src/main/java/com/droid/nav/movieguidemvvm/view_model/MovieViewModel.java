package com.droid.nav.movieguidemvvm.view_model;

import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;

import com.droid.nav.movieguidemvvm.BaseApplication;
import com.droid.nav.movieguidemvvm.model.Movie;
import com.droid.nav.movieguidemvvm.model.MoviesWraper;
import com.droid.nav.movieguidemvvm.network.MovieRepository;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by navdeepbedi on 21/04/18.
 */

public class MovieViewModel extends AndroidViewModel {

    private MutableLiveData<MoviesWraper> movieWraperLiveData;

    @Inject
    MovieRepository movieRepository;

    @Inject
    public MovieViewModel(@NonNull BaseApplication application, @NonNull MovieRepository movieRepository) {
        super(application);

        movieWraperLiveData = movieRepository.fetchMovies(1);
    }

    public void fetchMovieList(int page) {
        movieWraperLiveData = movieRepository.fetchMovies(page);

    }



    public LiveData<MoviesWraper> getMovieWrapper() {
        return movieWraperLiveData;
    }
}
