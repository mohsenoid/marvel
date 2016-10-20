package com.mirhoseini.marvel.view;

import com.mirhoseini.marvel.database.model.CharacterModel;

import java.util.List;

/**
 * Created by Mohsen on 20/10/2016.
 */

public interface CacheView extends BaseView {

    void setLast5CharactersCacheData(List<CharacterModel> characterModels);

    void showError(Throwable throwable);

}
