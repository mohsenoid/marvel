package com.mirhoseini.marvel.di.module;

import com.mirhoseini.marvel.Presentation.SearchPresenter;
import com.mirhoseini.marvel.Presentation.SearchPresenterImpl;
import com.mirhoseini.marvel.di.scope.Search;
import com.mirhoseini.marvel.domain.interactor.SearchInteractor;
import com.mirhoseini.marvel.domain.interactor.SearchInteractorImpl;
import com.mirhoseini.marvel.view.SearchView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Mohsen on 20/10/2016.
 */

@Module
public class SearchModule {

    private SearchView view;

    public SearchModule(SearchView view) {
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
        presenter.setView(view);
        return presenter;
    }

}
