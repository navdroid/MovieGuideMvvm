package com.droid.nav.movieguidemvp.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.squareup.moshi.Json;

public class Movie implements Parcelable
{
    private String id;
    private String overview;
    private String release_date;
    private String poster_path;
    private String backdrop_path;
    private String title;
    private double vote_average;

    public Movie()
    {

    }

    protected Movie(Parcel in)
    {
        id = in.readString();
        overview = in.readString();
        release_date = in.readString();
        poster_path = in.readString();
        backdrop_path = in.readString();
        title = in.readString();
        vote_average = in.readDouble();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>()
    {
        @Override
        public Movie createFromParcel(Parcel in)
        {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size)
        {
            return new Movie[size];
        }
    };

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getOverview()
    {
        return overview;
    }

    public void setOverview(String overview)
    {
        this.overview = overview;
    }

    public String getReleaseDate()
    {
        return release_date;
    }

    public void setReleaseDate(String releaseDate)
    {
        this.release_date = releaseDate;
    }

    public String getPosterPath()
    {
        return poster_path;
    }

    public void setPosterPath(String posterPath)
    {
        this.poster_path = posterPath;
    }

    public String getBackdropPath()
    {
        return backdrop_path;
    }

    public void setBackdropPath(String backdropPath)
    {
        this.backdrop_path = backdropPath;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public double getVoteAverage()
    {
        return vote_average;
    }

    public void setVoteAverage(double voteAverage)
    {
        this.vote_average = voteAverage;
    }

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i)
    {
        parcel.writeString(id);
        parcel.writeString(overview);
        parcel.writeString(release_date);
        parcel.writeString(poster_path);
        parcel.writeString(backdrop_path);
        parcel.writeString(title);
        parcel.writeDouble(vote_average);
    }
}
