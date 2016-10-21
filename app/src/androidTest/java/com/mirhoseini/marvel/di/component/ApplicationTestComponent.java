package com.mirhoseini.marvel.di.component;

import com.mirhoseini.marvel.di.module.AndroidModule;
import com.mirhoseini.marvel.di.module.ApiModule;
import com.mirhoseini.marvel.di.module.ApplicationModule;
import com.mirhoseini.marvel.di.module.ClientModule;
import com.mirhoseini.marvel.di.module.DatabaseModule;
import com.mirhoseini.marvel.view.activity.MainActivityTest;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Mohsen on 21/10/2016.
 */

@Singleton
@Component(modules = {
        AndroidModule.class,
        ApplicationModule.class,
        ApiModule.class,
        DatabaseModule.class,
        ClientModule.class
})
public interface ApplicationTestComponent extends ApplicationComponent {

    void inject(MainActivityTest activity);

}