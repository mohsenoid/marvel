package com.mirhoseini.marvel.di.module;

import com.mirhoseini.marvel.Presentation.CachePresenter;
import com.mirhoseini.marvel.Presentation.CachePresenterImpl;
import com.mirhoseini.marvel.di.scope.Cache;
import com.mirhoseini.marvel.view.CacheView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Mohsen on 20/10/2016.
 */

@Module
public class CacheModule {

    private CacheView view;

    public CacheModule(CacheView view) {
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
