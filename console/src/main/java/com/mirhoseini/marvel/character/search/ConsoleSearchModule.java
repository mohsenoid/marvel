package com.mirhoseini.marvel.character.search;

import dagger.Module;

/**
 * Created by Mohsen on 20/10/2016.
 */

@Module
public class ConsoleSearchModule extends SearchModule {

    ConsoleSearchModule(SearchView view) {
        super(view);
    }
}
