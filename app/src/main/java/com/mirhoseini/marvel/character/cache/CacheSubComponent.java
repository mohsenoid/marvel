package com.mirhoseini.marvel.character.cache;

import dagger.Subcomponent;

/**
 * Created by Mohsen on 20/10/2016.
 */

@Cache
@Subcomponent(modules = {
        CacheModule.class
})
public interface CacheSubComponent {

    void inject(CacheFragment fragment);

}
