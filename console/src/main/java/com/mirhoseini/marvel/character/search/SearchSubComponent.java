package com.mirhoseini.marvel.character.search;

import dagger.Subcomponent;

/**
 * Created by Mohsen on 20/10/2016.
 */

@Search
@Subcomponent(modules = {
        SearchModule.class
})
public interface SearchSubComponent {

    void inject(CharacterSearch characterSearch);

}
