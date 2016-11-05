package com.mirhoseini.marvel.character.cache;

import com.mirhoseini.marvel.base.BaseView;
import com.mirhoseini.marvel.database.model.CharacterModel;

import java.util.List;

/**
 * Created by Mohsen on 20/10/2016.
 */

interface CacheView extends BaseView {

    void setLast5CharactersCachedData(List<CharacterModel> characterModels);

    void showError(Throwable throwable);

}
