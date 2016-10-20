package com.mirhoseini.marvel.Presentation;

import com.mirhoseini.marvel.view.SearchView;

/**
 * Created by Mohsen on 20/10/2016.
 */

public interface SearchPresenter extends BasePresenter<SearchView> {

    void doSearch(boolean isConnected, String query, long timestamp);

    boolean isQueryValid(String query);

    void loadCharactersCachedData();

}
