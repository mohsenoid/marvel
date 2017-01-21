package com.mirhoseini.marvel.character.search;


import com.mirhoseini.marvel.database.DatabaseHelper;
import com.mirhoseini.marvel.database.mapper.Mapper;
import com.mirhoseini.marvel.database.model.CharacterModel;
import com.mirhoseini.marvel.domain.client.MarvelApi;
import com.mirhoseini.marvel.util.Constants;
import com.mirhoseini.marvel.util.HashGenerator;
import com.mirhoseini.marvel.util.SchedulerProvider;

import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;
import rx.exceptions.Exceptions;
import rx.subjects.ReplaySubject;

/**
 * Concrete implementation to search character from API.
 */
@Search
class SearchInteractor implements SearchContract.Interactor {

    private MarvelApi api;
    private SchedulerProvider scheduler;
    private DatabaseHelper databaseHelper;

    private ReplaySubject<CharacterModel> characterSubject;
    private Subscription characterSubscription;

    private ReplaySubject<List<CharacterModel>> allCharacterSubject;
    private Subscription allCharacterSubscription;

    @Inject
    SearchInteractor(MarvelApi api, SchedulerProvider scheduler, DatabaseHelper databaseHelper) {
        this.api = api;
        this.scheduler = scheduler;
        this.databaseHelper = databaseHelper;
    }

    @Override
    public Observable<CharacterModel> loadCharacter(String query,
                                                    long timestamp) {
        if (characterSubscription == null || characterSubscription.isUnsubscribed()) {
            characterSubject = ReplaySubject.create();

            // generate hash using timestamp and API keys
            String hash = HashGenerator.generate(timestamp, Constants.PRIVATE_KEY, Constants.PUBLIC_KEY);

            try {
                characterSubscription = Observable.concat(
                        Observable.just(databaseHelper.getCharacters(query)),
                        api.getCharacters(query, Constants.PUBLIC_KEY, hash, timestamp)
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
                                }))
                        .first(characterModel -> null != characterModel)
                        .subscribeOn(scheduler.backgroundThread())
                        .subscribe(characterSubject);

            } catch (SQLException e) {
                return Observable.error(e);
            }
        }

        return characterSubject.asObservable();
    }

    @Override
    public Observable<List<CharacterModel>> loadAllCharacters() {
        if (allCharacterSubscription == null || allCharacterSubscription.isUnsubscribed()) {
            allCharacterSubject = ReplaySubject.create();

            try {
                allCharacterSubscription = Observable.just(databaseHelper.selectAllCharacters())
                        .subscribeOn(scheduler.backgroundThread())
                        .subscribe(allCharacterSubject);

            } catch (SQLException e) {
                return Observable.error(e);
            }
        }

        return allCharacterSubject.asObservable();
    }


    @Override
    public void unbind() {
        if (characterSubscription != null && !characterSubscription.isUnsubscribed())
            characterSubscription.unsubscribe();

        if (allCharacterSubscription != null && !allCharacterSubscription.isUnsubscribed())
            allCharacterSubscription.unsubscribe();
    }

}
