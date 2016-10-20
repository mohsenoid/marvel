package com.mirhoseini.marvel.di.component;

import com.mirhoseini.marvel.di.module.AndroidModule;
import com.mirhoseini.marvel.di.module.ApiModule;
import com.mirhoseini.marvel.di.module.ApplicationModule;
import com.mirhoseini.marvel.di.module.ClientModule;
import com.mirhoseini.marvel.di.module.DatabaseModule;
import com.mirhoseini.marvel.di.module.MainModule;
import com.mirhoseini.marvel.view.activity.CharacterActivity;
import com.mirhoseini.marvel.view.activity.SplashActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Mohsen on 20/10/2016.
 */

@Singleton
@Component(modules = {
        AndroidModule.class,
        ApplicationModule.class,
        ApiModule.class,
        DatabaseModule.class,
        ClientModule.class
})
public interface ApplicationComponent {

    void inject(SplashActivity activity);

    void inject(CharacterActivity characterActivity);

    MainSubComponent plus(MainModule module);

}