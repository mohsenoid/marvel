package com.mirhoseini.marvel.Presentation;


import com.mirhoseini.marvel.view.CacheView;

/**
 * Created by Mohsen on 20/10/2016.
 */

public interface CachePresenter extends BasePresenter<CacheView> {

    void loadLast5CharactersCachedData();

}
