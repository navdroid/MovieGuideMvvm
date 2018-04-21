package com.droid.nav.movieguidemvp;

/**
 * Created by navdeepbedi on 21/04/18.
 */

public enum SortType
{
    MOST_POPULAR(0), HIGHEST_RATED(1), FAVORITES(2), NEWEST(3);

    private final int value;

    SortType(int value)
    {
        this.value = value;
    }

    public int getValue()
    {
        return value;
    }
}

