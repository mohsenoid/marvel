package com.mirhoseini.marvel.Presentation;


import com.mirhoseini.marvel.view.MainView;

/**
 * Created by Mohsen on 20/10/2016.
 */

public interface MainPresenter extends BasePresenter<MainView> {

    void doSearch(boolean isConnected, String query, long timestamp);

    boolean isQueryValid(String query);

    void loadCharactersCacheData();

    void loadLast5CharactersCacheData();
}
