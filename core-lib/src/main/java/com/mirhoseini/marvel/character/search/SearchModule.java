package com.mirhoseini.marvel.character.search;

import dagger.Module;
import dagger.Provides;

/**
 * This is a Dagger module used to pass in Search Presenter and Interactor dependencies.
 */
@Module
public class SearchModule {

    @Provides
    @Search
    public SearchContract.Interactor provideInteractor(SearchInteractor interactor) {
        return interactor;
    }

    @Provides
    @Search
    public SearchContract.Presenter providePresenter(SearchPresenter presenter) {
        return presenter;
    }

}
