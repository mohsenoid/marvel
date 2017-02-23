package com.mirhoseini.marvel.network;

import com.mirhoseini.marvel.network.client.ApiModule;
import com.mirhoseini.marvel.network.client.MarvelApi;

import retrofit2.Retrofit;

import static org.mockito.Mockito.mock;

/**
 * Created by Mohsen on 21/10/2016.
 */

public class ApiTestModule extends ApiModule {

    @Override
    public MarvelApi provideMarvelApiService(Retrofit retrofit) {
        // replace real MarvelApi with Mock one
        return mock(MarvelApi.class);
    }

}
