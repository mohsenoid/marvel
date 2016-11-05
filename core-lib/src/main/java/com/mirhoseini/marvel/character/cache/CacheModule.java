package com.mirhoseini.marvel.character.cache;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Mohsen on 20/10/2016.
 */

@Module
class CacheModule {

    private CacheView view;

    CacheModule(CacheView view) {
        this.view = view;
    }

    @Provides
    public CacheView provideView() {
        return view;
    }

    @Provides
    @Cache
    public CachePresenter providePresenter(CachePresenterImpl presenter) {
        presenter.setView(view);
        return presenter;
    }

}
