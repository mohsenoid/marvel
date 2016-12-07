package com.mirhoseini.marvel.character.search;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Mohsen on 20/10/2016.
 */

@Module
public class AppSearchModule extends SearchModule {
    private final CharacterSearchFragment.OnListFragmentInteractionListener listener;

    public AppSearchModule(Context context, CharacterSearchFragment fragment) {
        super(fragment);

        if (context instanceof CharacterSearchFragment.OnListFragmentInteractionListener) {
            listener = (CharacterSearchFragment.OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Provides
    @Search
    CharacterSearchFragment.OnListFragmentInteractionListener provideOnListFragmentInteractionListener() {
        return listener;
    }

}
