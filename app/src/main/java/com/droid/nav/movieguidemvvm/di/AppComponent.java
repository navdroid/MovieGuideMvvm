package com.droid.nav.movieguidemvvm.di;



import com.droid.nav.movieguidemvvm.BaseApplication;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

/**
 * Created by navdeepbedi on 18/03/18.
 */

@Singleton
@Component(modules = {
        AndroidInjectionModule.class,
        AppModule.class,
        MainActivityModule.class
})
public interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(BaseApplication application);
        AppComponent build();
    }

    void inject(BaseApplication application);
}
