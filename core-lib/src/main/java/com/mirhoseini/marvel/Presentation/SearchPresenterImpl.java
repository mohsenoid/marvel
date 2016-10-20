package com.mirhoseini.marvel.Presentation;

import com.mirhoseini.marvel.database.DatabaseHelper;
import com.mirhoseini.marvel.database.mapper.Mapper;
import com.mirhoseini.marvel.database.model.CharacterModel;
import com.mirhoseini.marvel.domain.interactor.SearchInteractor;
import com.mirhoseini.marvel.util.Constants;
import com.mirhoseini.marvel.view.SearchView;

import java.sql.SQLException;

import javax.inject.Inject;

import rx.Subscription;
import rx.subscriptions.Subscriptions;

/**
 * Created by Mohsen on 20/10/2016.
 */

public class SearchPresenterImpl implements SearchPresenter {

    @Inject
    SearchInteractor interactor;
    @Inject
    DatabaseHelper databaseHelper;

    private SearchView view;
    private Subscription subscription = Subscriptions.empty();

    @Inject
    public SearchPresenterImpl() {
    }

    @Override
    public void setView(SearchView view) {
        this.view = view;
    }


    @Override
    public void doSearch(boolean isConnected, String query, long timestamp) {
        if (null != view) {
            view.showProgress();
        }

        subscription = interactor.loadCharacter(query, Constants.PRIVATE_KEY, Constants.PUBLIC_KEY, timestamp)
                .subscribe(charactersResponse -> {
                            // check if result code is OK
                            if (Constants.CODE_OK == charactersResponse.getCode()) {
                                // check if is there any result
                                if (charactersResponse.getData().getCount() > 0) {
                                    CharacterModel character = Mapper.mapCharacterResponseToCharacter(charactersResponse);

                                    // cache data on database
                                    try {
                                        databaseHelper.addCharacter(character);
                                    } catch (SQLException e) {
                                        if (null != view) {
                                            view.hideProgress();
                                            view.showError(e);
                                        }
                                    }

                                    if (null != view) {
                                        view.hideProgress();
                                        view.showCharacter(character);

                                        if (!isConnected)
                                            view.showOfflineMessage();
                                    }
                                } else {
                                    // show no character found
                                    if (null != view) {
                                        view.hideProgress();
                                        view.showQueryNoResult();
                                    }
                                }
                            } else {
                                // show query error
                                if (null != view) {
                                    view.hideProgress();
                                    view.showQueryError(new Throwable(charactersResponse.getStatus()));
                                }
                            }
                        },
                        // handle exceptions
                        throwable -> {
                            if (null != view) {
                                view.hideProgress();
                            }

                            if (isConnected) {
                                if (null != view) {
                                    view.showRetryMessage(null);
                                }
                            } else {
                                if (null != view) {
                                    view.showOfflineMessage();
                                }
                            }
                        });
    }

    @Override
    public boolean isQueryValid(String query) {
        return null != query && !query.isEmpty();
    }

    @Override
    public void loadCharactersCachedData() {
        if (null != view)
            try {
                view.setCharactersCachedData(databaseHelper.selectAllCharacters());
            } catch (SQLException e) {
                view.showError(e);
            }
    }

    @Override
    public void destroy() {
        if (subscription != null && !subscription.isUnsubscribed())
            subscription.unsubscribe();

        interactor.onDestroy();

        view = null;
        interactor = null;
    }
}
