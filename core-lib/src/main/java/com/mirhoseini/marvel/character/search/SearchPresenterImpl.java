package com.mirhoseini.marvel.character.search;

import com.mirhoseini.marvel.database.DatabaseHelper;
import com.mirhoseini.marvel.database.mapper.Mapper;
import com.mirhoseini.marvel.util.Constants;
import com.mirhoseini.marvel.util.SchedulerProvider;

import java.sql.SQLException;

import javax.inject.Inject;

import rx.Subscription;
import rx.exceptions.Exceptions;
import rx.subscriptions.Subscriptions;

/**
 * Created by Mohsen on 20/10/2016.
 */

class SearchPresenterImpl implements SearchPresenter {

    @Inject
    SearchInteractor interactor;
    @Inject
    DatabaseHelper databaseHelper;

    private SearchView view;
    private Subscription subscription = Subscriptions.empty();
    private SchedulerProvider scheduler;

    @Inject
    public SearchPresenterImpl(SchedulerProvider scheduler) {
        this.scheduler = scheduler;
    }

    @Override
    public void bind(SearchView view) {
        this.view = view;
    }


    @Override
    public void doSearch(boolean isConnected, String query, long timestamp) {
        if (null != view) {
            view.showProgress();
        }

        subscription = interactor.loadCharacter(query, Constants.PRIVATE_KEY, Constants.PUBLIC_KEY, timestamp)
                // check if result code is OK
                .map(charactersResponse -> {
                    if (Constants.CODE_OK == charactersResponse.getCode())
                        return charactersResponse;
                    else
                        throw Exceptions.propagate(new ApiResponseCodeException(charactersResponse.getCode(), charactersResponse.getStatus()));
                })
                // check if is there any result
                .map(charactersResponse -> {
                    if (charactersResponse.getData().getCount() > 0)
                        return charactersResponse;
                    else
                        throw Exceptions.propagate(new NoSuchCharacterException());
                })
                // map CharacterResponse to CharacterModel
                .map(Mapper::mapCharacterResponseToCharacter)
                // cache data on database
                .map(character -> {
                    try {
                        databaseHelper.addCharacter(character);
                    } catch (SQLException e) {
                        throw Exceptions.propagate(e);
                    }

                    return character;
                })
                .observeOn(scheduler.mainThread())
                .subscribe(character -> {
                            if (null != view) {
                                view.hideProgress();
                                view.showCharacter(character);

                                if (!isConnected)
                                    view.showOfflineMessage();
                            }
                        },
                        // handle exceptions
                        throwable -> {
                            if (null != view) {
                                view.hideProgress();
                            }

                            if (isConnected) {
                                if (null != view) {
                                    if (throwable instanceof ApiResponseCodeException)
                                        view.showServiceError((ApiResponseCodeException) throwable);
                                    else if (throwable instanceof NoSuchCharacterException)
                                        view.showQueryNoResult();
                                    else
                                        view.showRetryMessage(throwable);
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
    public void unbind() {
        if (subscription != null && !subscription.isUnsubscribed())
            subscription.unsubscribe();

        interactor.unbind();

        view = null;
        interactor = null;
    }
}
