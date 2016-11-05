package com.mirhoseini.marvel.character.search;

import com.mirhoseini.marvel.base.BaseInteractor;
import com.mirhoseini.marvel.domain.model.CharactersResponse;

import rx.Observable;

/**
 * Created by Mohsen on 20/10/2016.
 */

interface SearchInteractor extends BaseInteractor {

    Observable<CharactersResponse> loadCharacter(String query, String privateKey, String publicKey, long timestamp);

}
