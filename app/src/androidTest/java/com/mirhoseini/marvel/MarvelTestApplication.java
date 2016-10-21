package com.mirhoseini.marvel;

import com.mirhoseini.marvel.di.component.ApplicationTestComponent;
import com.mirhoseini.marvel.di.component.DaggerApplicationTestComponent;
import com.mirhoseini.marvel.di.module.AndroidModule;
import com.mirhoseini.marvel.di.module.ApiTestModule;

/**
 * Created by Mohsen on 21/10/2016.
 */

public class MarvelTestApplication extends MarvelApplicationImpl {

    @Override
    public ApplicationTestComponent createComponent() {
        return DaggerApplicationTestComponent
                .builder()
                .androidModule(new AndroidModule(this))
                // replace Api Module with Mocked one
                .apiModule(new ApiTestModule())
                .build();
    }

}
