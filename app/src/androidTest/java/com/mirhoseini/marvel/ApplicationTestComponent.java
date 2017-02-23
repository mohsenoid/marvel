package com.mirhoseini.marvel;

import com.mirhoseini.marvel.activity.MainActivityTest;
import com.mirhoseini.marvel.storage.DatabaseModule;
import com.mirhoseini.marvel.network.client.ApiModule;
import com.mirhoseini.marvel.network.client.ClientModule;

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