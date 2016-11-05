package com.mirhoseini.marvel.character.search;

import com.mirhoseini.marvel.base.BasePresenter;

/**
 * Created by Mohsen on 20/10/2016.
 */

interface SearchPresenter extends BasePresenter<SearchView> {

    void doSearch(boolean isConnected, String query, long timestamp);

    boolean isQueryValid(String query);

    void loadCharactersCachedData();

}
