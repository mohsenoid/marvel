package com.mirhoseini.marvel.character.cache;

import com.mirhoseini.marvel.base.BasePresenter;
import com.mirhoseini.marvel.base.BaseView;
import com.mirhoseini.marvel.database.model.CharacterModel;

import java.util.List;

/**
 * This specifies the contract between Cache view and Cache presenter.
 */
interface CacheContract {

    interface View extends BaseView {

        void setLast5CharactersCachedData(List<CharacterModel> characterModels);

        void showError(Throwable throwable);

    }

    interface Presenter extends BasePresenter<View> {

        void loadLast5CharactersCachedData();

    }

}
