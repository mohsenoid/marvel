package com.mirhoseini.marvel.view;

import com.mirhoseini.marvel.database.model.CharacterModel;
import com.mirhoseini.marvel.domain.model.CharactersResponse;

import java.util.List;

/**
 * Created by Mohsen on 20/10/2016.
 */

public interface MainView extends BaseView {

    void setCharactersCacheData(List<CharacterModel> characters);

    void showProgress();

    void hideProgress();

    void showQueryError(Throwable throwable);

    void showCharacter(CharacterModel character);

    void showRetryMessage(Throwable throwable);

    void showQueryNoResult();

    void setLast5CharactersCacheData(List<CharacterModel> characterModels);

}
