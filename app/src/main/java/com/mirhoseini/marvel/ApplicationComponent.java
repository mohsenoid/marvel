package com.mirhoseini.marvel;

import com.mirhoseini.marvel.activity.CharacterActivity;
import com.mirhoseini.marvel.activity.MainActivity;
import com.mirhoseini.marvel.activity.SplashActivity;
import com.mirhoseini.marvel.character.cache.CacheModule;
import com.mirhoseini.marvel.character.cache.CacheSubComponent;
import com.mirhoseini.marvel.character.search.SearchModule;
import com.mirhoseini.marvel.character.search.SearchSubComponent;
import com.mirhoseini.marvel.database.DatabaseModule;
import com.mirhoseini.marvel.domain.ApiModule;
import com.mirhoseini.marvel.domain.ClientModule;

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

    void inject(MainActivity activity);

    void inject(CharacterActivity characterActivity);

    SearchSubComponent plus(SearchModule module);

    CacheSubComponent plus(CacheModule module);

}