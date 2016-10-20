package com.mirhoseini.marvel.domain.interactor;

import com.mirhoseini.marvel.domain.model.CharactersResponse;

import rx.Observable;

/**
 * Created by Mohsen on 20/10/2016.
 */

public interface CharactersInteractor {

    Observable<CharactersResponse> loadCharacter(String query, String privateKey, String publicKey, long timestamp);

    void onDestroy();

}
