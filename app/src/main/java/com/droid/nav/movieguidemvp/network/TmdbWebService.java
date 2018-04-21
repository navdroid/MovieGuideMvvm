package com.droid.nav.movieguidemvp.network;


import com.droid.nav.movieguidemvp.model.MoviesWraper;
import com.droid.nav.movieguidemvp.model.ReviewsWrapper;
import com.droid.nav.movieguidemvp.model.VideoWrapper;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by navdeep on 8/20/2017.
 */

public interface TmdbWebService {

    @GET("3/discover/movie?language=en&sort_by=popularity.desc")
    Call<MoviesWraper> popularMovies(@Query("page") int page);

    @GET("3/discover/movie?vote_count.gte=500&language=en&sort_by=vote_average.desc")
    Call<MoviesWraper> highestRatedMovies(@Query("page") int page);

    @GET("3/discover/movie?language=en&sort_by=release_date.desc")
    Call<MoviesWraper> newestMovies(@Query("release_date.lte") String maxReleaseDate, @Query("vote_count.gte") int minVoteCount);

    @GET("3/movie/{movieId}/videos")
    Call<VideoWrapper> trailers(@Path("movieId") String movieId);

    @GET("3/movie/{movieId}/reviews")
    Call<ReviewsWrapper> reviews(@Path("movieId") String movieId);

}
