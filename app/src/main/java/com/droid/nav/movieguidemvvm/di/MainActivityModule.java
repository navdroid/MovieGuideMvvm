package com.droid.nav.movieguidemvvm.di;


import com.droid.nav.movieguidemvvm.view.MoviesListingActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by navdeepbedi on 18/03/18.
 */


@Module
public abstract class MainActivityModule {

    @ContributesAndroidInjector(modules = FragmentBuildersModule.class)
    abstract MoviesListingActivity contributeMainActivity();
}
