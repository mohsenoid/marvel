package com.mirhoseini.marvel.di.component;

import com.mirhoseini.marvel.di.module.AppCacheModule;
import com.mirhoseini.marvel.di.scope.Cache;
import com.mirhoseini.marvel.view.fragment.CharacterCacheFragment;

import dagger.Subcomponent;

/**
 * Created by Mohsen on 20/10/2016.
 */

@Cache
@Subcomponent(modules = {
        AppCacheModule.class
})
public interface CacheSubComponent {

    void inject(CharacterCacheFragment fragment);

}
