package com.mirhoseini.marvel.character.search;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Mohsen on 20/10/2016.
 */

@Module
class SearchModule {

    private SearchView view;

    SearchModule(SearchView view) {
        this.view = view;
    }

    @Provides
    public SearchView provideView() {
        return view;
    }

    @Provides
    @Search
    public SearchInteractor provideInteractor(SearchInteractorImpl interactor) {
        return interactor;
    }

    @Provides
    @Search
    public SearchPresenter providePresenter(SearchPresenterImpl presenter) {
        presenter.bind(view);
        return presenter;
    }

}
