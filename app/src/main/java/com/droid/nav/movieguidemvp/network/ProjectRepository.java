package com.droid.nav.movieguidemvp.network;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.droid.nav.movieguidemvp.SortType;
import com.droid.nav.movieguidemvp.model.Movie;
import com.droid.nav.movieguidemvp.model.MoviesWraper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by navdeepbedi on 21/04/18.
 */

@Singleton
public class ProjectRepository {

    private TmdbWebService tmdbWebService;
    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static final int NEWEST_MIN_VOTE_COUNT = 50;


    @Inject
    public ProjectRepository(TmdbWebService apiService) {

        this.tmdbWebService = apiService;

    }

    public MutableLiveData<MoviesWraper> initMovieList() {
        final MutableLiveData<MoviesWraper> data = new MutableLiveData<>();
        return data;
    }

    public MutableLiveData<List<Movie>> getMoviesFirstPage() {
        MutableLiveData<List<Movie>> data=new MutableLiveData<>();

        return  fetchMovies(data,1);
    }

    public MutableLiveData<List<Movie>> fetchMovies(MutableLiveData<List<Movie>> data, int page) {



        tmdbWebService.popularMovies(page).enqueue(new Callback<MoviesWraper>() {
            @Override
            public void onResponse(Call<MoviesWraper> call, Response<MoviesWraper> response) {

                if(data.getValue()!=null) {
                    List<Movie> old = data.getValue();
                    old.addAll(response.body().getMovieList());
                    data.setValue(old);
                }
                else
                    data.setValue(response.body().getMovieList());


            }

            @Override
            public void onFailure(Call<MoviesWraper> call, Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }
}
