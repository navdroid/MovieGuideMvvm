package com.droid.nav.movieguidemvvm.di;

import com.droid.nav.movieguidemvvm.view_model.MovieViewModel;

import dagger.Subcomponent;

/**
 * Created by navdeepbedi on 18/03/18.
 */

@Subcomponent
public interface ViewModelSubComponent {
    @Subcomponent.Builder
    interface Builder {
        ViewModelSubComponent build();
    }

    MovieViewModel movieViewModel();
}

