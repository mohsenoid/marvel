package com.mirhoseini.marvel.di.component;

import com.mirhoseini.marvel.di.module.MainModule;
import com.mirhoseini.marvel.di.scope.MainScope;
import com.mirhoseini.marvel.view.activity.MainActivity;

import dagger.Subcomponent;

/**
 * Created by Mohsen on 20/10/2016.
 */

@MainScope
@Subcomponent(modules = {
        MainModule.class
})
public interface MainSubComponent {

    void inject(MainActivity activity);

}
