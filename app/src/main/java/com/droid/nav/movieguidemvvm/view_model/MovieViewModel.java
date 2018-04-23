package com.droid.nav.movieguidemvvm.view_model;

import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.droid.nav.movieguidemvvm.BaseApplication;
import com.droid.nav.movieguidemvvm.model.Movie;
import com.droid.nav.movieguidemvvm.network.ProjectRepository;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by navdeepbedi on 21/04/18.
 */

public class MovieViewModel extends AndroidViewModel {

    private MutableLiveData<List<Movie>> movieLiveData;

    @Inject
    ProjectRepository projectRepository;

    @Inject
    public MovieViewModel(@NonNull BaseApplication application, @NonNull ProjectRepository projectRepository) {
        super(application);

        movieLiveData=projectRepository.getMoviesFirstPage();
    }

    public void fetchMovieList(int page) {
        movieLiveData=projectRepository.fetchMovies(movieLiveData,page);

    }

    public LiveData<List<Movie>> getMovieList() {
        return movieLiveData;
    }
}
