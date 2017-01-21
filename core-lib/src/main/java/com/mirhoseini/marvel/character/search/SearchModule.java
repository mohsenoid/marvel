package com.mirhoseini.marvel.character.search;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Mohsen on 20/10/2016.
 */

@Module
public class SearchModule {

    @Provides
    @Search
    public SearchInteractor provideInteractor(SearchInteractorImpl interactor) {
        return interactor;
    }

    @Provides
    @Search
    public SearchPresenter providePresenter(SearchPresenterImpl presenter) {
        return presenter;
    }

}
