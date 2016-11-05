package com.mirhoseini.marvel.character.cache;


import com.mirhoseini.marvel.base.BasePresenter;

/**
 * Created by Mohsen on 20/10/2016.
 */

interface CachePresenter extends BasePresenter<CacheView> {

    void loadLast5CharactersCachedData();

}
