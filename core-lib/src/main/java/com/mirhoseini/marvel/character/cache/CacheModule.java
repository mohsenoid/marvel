package com.mirhoseini.marvel.character.cache;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Mohsen on 20/10/2016.
 */

@Module
public class CacheModule {

    @Provides
    @Cache
    public CachePresenter providePresenter(CachePresenterImpl presenter) {
        return presenter;
    }

}
