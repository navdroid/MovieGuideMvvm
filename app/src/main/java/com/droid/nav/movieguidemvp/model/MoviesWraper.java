package com.droid.nav.movieguidemvp.model;

import com.squareup.moshi.Json;

import java.util.List;

/**
 * Created by navdeepbedi on 8/20/2017.
 */

public class MoviesWraper {

    private List<Movie> results;

    public List<Movie> getMovieList() {
        return results;
    }

    public void setMovieList(List<Movie> results) {
        this.results = results;
    }
}
