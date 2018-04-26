package com.droid.nav.movieguidemvvm.network;

import android.arch.lifecycle.MutableLiveData;
import android.databinding.ObservableList;

import com.droid.nav.movieguidemvvm.model.Movie;
import com.droid.nav.movieguidemvvm.model.MoviesWraper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by navdeepbedi on 21/04/18.
 */

@Singleton
public class MovieRepository {

    private TmdbWebService tmdbWebService;
    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private static final int NEWEST_MIN_VOTE_COUNT = 50;
    private  MutableLiveData<MoviesWraper> data;


    @Inject
    public MovieRepository(TmdbWebService apiService) {

        this.tmdbWebService = apiService;

    }

    public MutableLiveData<MoviesWraper> initMovieList() {
        data = new MutableLiveData<>();
        return data;
    }



    public MutableLiveData<MoviesWraper> fetchMovies( int page) {

        if(page==1)
            initMovieList();


        tmdbWebService.popularMovies(page).enqueue(new Callback<MoviesWraper>() {
            @Override
            public void onResponse(Call<MoviesWraper> call, Response<MoviesWraper> response) {

                    data.setValue(response.body());


            }

            @Override
            public void onFailure(Call<MoviesWraper> call, Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }


}
