package com.mirhoseini.marvel;

import android.app.Application;
import android.content.Context;

import com.mirhoseini.marvel.character.cache.AppCacheModule;
import com.mirhoseini.marvel.character.cache.CacheSubComponent;
import com.mirhoseini.marvel.character.cache.CharacterCacheFragment;
import com.mirhoseini.marvel.character.search.AppSearchModule;
import com.mirhoseini.marvel.character.search.CharacterSearchFragment;
import com.mirhoseini.marvel.character.search.SearchSubComponent;

/**
 * Created by Mohsen on 20/10/2016.
 */

public abstract class MarvelApplication extends Application {

    private static ApplicationComponent component;
    private CacheSubComponent cacheSubComponent;
    private SearchSubComponent searchSubComponent;

    public static ApplicationComponent getComponent() {
        return component;
    }

    public CacheSubComponent getCacheSubComponent() {
        return cacheSubComponent;
    }

    public SearchSubComponent getSearchSubComponent() {
        return searchSubComponent;
    }

    public static MarvelApplication get(Context context) {
        return (MarvelApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        initApplication();

        component = createComponent();
    }

    public ApplicationComponent createComponent() {
        return DaggerApplicationComponent.builder()
                .androidModule(new AndroidModule(this))
                .build();
    }

    public CacheSubComponent createCacheSubComponent(Context context, CharacterCacheFragment fragment, int columnCount) {
        cacheSubComponent = component.plus(new AppCacheModule(context, fragment, columnCount));
        return cacheSubComponent;
    }

    public void releaseCacheSubComponent() {
        cacheSubComponent = null;
    }

    public SearchSubComponent createSearchSubComponent(Context context, CharacterSearchFragment fragment) {
        searchSubComponent = component.plus(new AppSearchModule(context, fragment));
        return searchSubComponent;
    }

    public void releaseSearchSubComponent() {
        searchSubComponent = null;
    }


    public abstract void initApplication();

}
