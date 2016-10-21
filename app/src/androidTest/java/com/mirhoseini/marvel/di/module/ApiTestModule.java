package com.mirhoseini.marvel.di.module;

import com.mirhoseini.marvel.domain.client.MarvelApi;

import retrofit2.Retrofit;

import static org.mockito.Mockito.mock;

/**
 * Created by Mohsen on 21/10/2016.
 */

public class ApiTestModule extends ApiModule {

    @Override
    public MarvelApi provideMarvelApiService(Retrofit retrofit) {
        return mock(MarvelApi.class);
    }

}
