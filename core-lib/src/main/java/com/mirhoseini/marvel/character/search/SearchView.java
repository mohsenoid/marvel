package com.mirhoseini.marvel.character.search;

import com.mirhoseini.marvel.base.BaseView;
import com.mirhoseini.marvel.database.model.CharacterModel;

import java.util.List;

/**
 * Created by Mohsen on 20/10/2016.
 */

interface SearchView extends BaseView {

    void showQueryError(Throwable throwable);

    void showCharacter(CharacterModel character);

    void showRetryMessage(Throwable throwable);

    void showQueryNoResult();

    void setCharactersCachedData(List<CharacterModel> characters);

    void showError(Throwable throwable);

    void showProgress();

    void hideProgress();

    void showServiceError(ApiResponseCodeException throwable);

}
