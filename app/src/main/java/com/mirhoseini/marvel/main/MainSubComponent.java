package com.mirhoseini.marvel.main;

import com.mirhoseini.marvel.PerActivity;

import dagger.Subcomponent;

/**
 * Created by Mohsen on 22/01/2017.
 */
@PerActivity
@Subcomponent(modules = MainModule.class)
public interface MainSubComponent {

    void inject(MainActivity activity);

}
