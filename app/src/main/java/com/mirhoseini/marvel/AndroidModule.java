package com.mirhoseini.marvel;

import android.content.Context;
import android.content.res.Resources;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger AndroidModule holding Android related providers for Dagger injections.
 * Created by Mohsen on 20/10/2016.
 */

@Module
public class AndroidModule {
    /**
     * MarvelApplication instance used during app run.
     */
    private final MarvelApplication application;

    /**
     * AndroidModule constructor, which get application as parameter.
     *
     * @param application Marvel Application instance
     */
    public AndroidModule(final MarvelApplication application) {
        this.application = application;
    }

    /**
     * Provide Application Context for Dagger injection.
     * @return Application Context
     */
    @Provides
    @Singleton
    Context provideContext() {
        return application.getApplicationContext();
    }

    /**
     * Provide Application Resources for Dagger injection.
     * @return Application Resources
     */
    @Provides
    @Singleton
    Resources provideResources() {
        return application.getResources();
    }

}
