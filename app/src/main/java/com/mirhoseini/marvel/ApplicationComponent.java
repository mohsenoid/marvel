package com.mirhoseini.marvel;

import com.mirhoseini.marvel.character.CharacterActivity;
import com.mirhoseini.marvel.character.cache.CacheModule;
import com.mirhoseini.marvel.character.cache.CacheSubComponent;
import com.mirhoseini.marvel.character.search.SearchModule;
import com.mirhoseini.marvel.character.search.SearchSubComponent;
import com.mirhoseini.marvel.storage.DatabaseModule;
import com.mirhoseini.marvel.network.client.ApiModule;
import com.mirhoseini.marvel.network.client.ClientModule;
import com.mirhoseini.marvel.main.MainModule;
import com.mirhoseini.marvel.main.MainSubComponent;
import com.mirhoseini.marvel.splash.SplashActivity;

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

    SearchSubComponent plus(SearchModule module);

    CacheSubComponent plus(CacheModule module);

}