package com.mirhoseini.marvel.di.module;

import com.mirhoseini.marvel.Presentation.MainPresenter;
import com.mirhoseini.marvel.Presentation.MainPresenterImpl;
import com.mirhoseini.marvel.di.scope.MainScope;
import com.mirhoseini.marvel.domain.interactor.CharactersInteractor;
import com.mirhoseini.marvel.domain.interactor.MainInteractorImpl;
import com.mirhoseini.marvel.view.MainView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Mohsen on 20/10/2016.
 */

@Module
public class MainModule {

    private MainView view;

    public MainModule(MainView view) {
        this.view = view;
    }

    @Provides
    public MainView provideView() {
        return view;
    }

    @Provides
    @MainScope
    public CharactersInteractor provideInteractor(MainInteractorImpl interactor) {
        return interactor;
    }

    @Provides
    @MainScope
    public MainPresenter providePresenter(MainPresenterImpl presenter) {
        presenter.setView(view);
        return presenter;
    }

}
