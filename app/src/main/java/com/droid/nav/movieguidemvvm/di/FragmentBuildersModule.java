package com.droid.nav.movieguidemvvm.di;


//import com.droid.nav.movieguidemvp.view.MoviesListingFragment;

import com.droid.nav.movieguidemvvm.view.MoviesListingFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by navdeepbedi on 18/03/18.
 */

@Module
public abstract class FragmentBuildersModule {


    @ContributesAndroidInjector
    abstract MoviesListingFragment contributeMovieListingFragment();
}
