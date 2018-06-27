package com.mirhoseini.marvel.domain.client;

import com.mirhoseini.marvel.domain.model.CharactersResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Mohsen on 20/10/2016.
 */

public interface MarvelApi {
    String NAME = "name";
    String API_KEY = "apikey";
    String HASH = "hash";
    String TIMESTAMP = "ts";

    // http://gateway.marvel.com:80/v1/public/characters?name=Iron%20Man&apikey=PUBLIC_API_KEY&hash=HASH&ts=TIMESTAMP
    @GET("v1/public/characters")
    Single<CharactersResponse> getCharacters(
            @Query(NAME) String query,
            @Query(API_KEY) String publicKey,
            @Query(HASH) String hash,
            @Query(TIMESTAMP) long timestamp);
}
