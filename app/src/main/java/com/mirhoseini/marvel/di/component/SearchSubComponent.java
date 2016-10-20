package com.mirhoseini.marvel.di.component;

import com.mirhoseini.marvel.di.module.AppSearchModule;
import com.mirhoseini.marvel.di.scope.Search;
import com.mirhoseini.marvel.view.fragment.CharacterSearchFragment;

import dagger.Subcomponent;

/**
 * Created by Mohsen on 20/10/2016.
 */

@Search
@Subcomponent(modules = {
        AppSearchModule.class
})
public interface SearchSubComponent {

    void inject(CharacterSearchFragment fragment);

}
