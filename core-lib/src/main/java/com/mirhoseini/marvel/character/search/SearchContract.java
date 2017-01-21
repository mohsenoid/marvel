package com.mirhoseini.marvel.character.search;

import com.mirhoseini.marvel.base.BaseInteractor;
import com.mirhoseini.marvel.base.BasePresenter;
import com.mirhoseini.marvel.base.BaseView;
import com.mirhoseini.marvel.database.model.CharacterModel;
import com.mirhoseini.marvel.domain.model.CharactersResponse;

import java.util.List;

import rx.Observable;

/**
 * This specifies the contract between Search view, Search presenter and Search Interactor.
 */
interface SearchContract {

    interface View extends BaseView {

        void showQueryError(Throwable throwable);

        void showCharacter(CharacterModel character);

        void showRetryMessage(Throwable throwable);

        void showQueryNoResult();

        void setCharactersCachedData(List<CharacterModel> characters);

        void showError(Throwable throwable);

        void showDatabaseError(Throwable throwable);

        void showProgress();

        void hideProgress();

        void showServiceError(ApiResponseCodeException throwable);

    }

    interface Presenter extends BasePresenter<View> {

        void doSearch(boolean isConnected, String query, long timestamp);

        boolean isQueryValid(String query);

        void loadCharactersCachedData();

    }

    interface Interactor extends BaseInteractor {

        Observable<CharacterModel> loadCharacter(String query, long timestamp);

        Observable<List<CharacterModel>> loadAllCharacters();
    }

}
