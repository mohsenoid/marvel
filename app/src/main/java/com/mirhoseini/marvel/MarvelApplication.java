package com.mirhoseini.marvel;

import android.app.Application;
import android.content.Context;

import com.mirhoseini.marvel.character.cache.CacheModule;
import com.mirhoseini.marvel.character.cache.CacheSubComponent;
import com.mirhoseini.marvel.character.search.SearchModule;
import com.mirhoseini.marvel.character.search.SearchSubComponent;

/**
 * Marvel Application class used for general variables required during application run.
 * Created by Mohsen on 20/10/2016.
 */

public abstract class MarvelApplication extends Application {

    /** Application main component used for injection. */
    private static ApplicationComponent component;
    /** Cache subComponent used for injection. */
    private CacheSubComponent cacheSubComponent;
    /** Search subComponent used for injection. */
    private SearchSubComponent searchSubComponent;

    public static ApplicationComponent getComponent() {
        return component;
    }

    public static MarvelApplication get(Context context) {
        return (MarvelApplication) context.getApplicationContext();
    }

    public CacheSubComponent getCacheSubComponent() {
        if (null == cacheSubComponent)
            createCacheSubComponent();

        return cacheSubComponent;
    }

    public CacheSubComponent createCacheSubComponent() {
        cacheSubComponent = component.plus(new CacheModule());
        return cacheSubComponent;
    }

    public void releaseCacheSubComponent() {
        cacheSubComponent = null;
    }

    public SearchSubComponent getSearchSubComponent() {
        if (null == searchSubComponent)
            createSearchSubComponent();

        return searchSubComponent;
    }

    public SearchSubComponent createSearchSubComponent() {
        searchSubComponent = component.plus(new SearchModule());
        return searchSubComponent;
    }

    public void releaseSearchSubComponent() {
        searchSubComponent = null;
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

    public abstract void initApplication();

}
