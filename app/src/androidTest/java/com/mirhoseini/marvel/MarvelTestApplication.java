package com.mirhoseini.marvel;


import com.mirhoseini.marvel.domain.ApiTestModule;

/**
 * Created by Mohsen on 21/10/2016.
 */

public class MarvelTestApplication extends MarvelApplicationImpl {

    @Override
    public ApplicationTestComponent createComponent() {
        return DaggerApplicationTestComponent
                .builder()
                .androidModule(new AndroidModule(this))
                // replace Api Module with Mock one
                .apiModule(new ApiTestModule())
                .build();
    }

}
