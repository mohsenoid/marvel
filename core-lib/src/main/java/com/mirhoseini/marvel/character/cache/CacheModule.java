package com.mirhoseini.marvel.character.cache;

import dagger.Module;
import dagger.Provides;

/**
 * This is a Dagger module used to pass in Cache Presenter dependency.
 */
@Module
public class CacheModule {

    @Provides
    @Cache
    public CacheContract.Presenter providePresenter(CachePresenter presenter) {
        return presenter;
    }

}
