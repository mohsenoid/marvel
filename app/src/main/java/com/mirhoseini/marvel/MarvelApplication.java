package com.mirhoseini.marvel;

import android.app.Application;

import com.mirhoseini.marvel.di.component.ApplicationComponent;
import com.mirhoseini.marvel.di.component.DaggerApplicationComponent;
import com.mirhoseini.marvel.di.module.AndroidModule;

/**
 * Created by Mohsen on 20/10/2016.
 */

public abstract class MarvelApplication extends Application {

    private static ApplicationComponent component;

    public static ApplicationComponent getComponent() {
        return component;
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
