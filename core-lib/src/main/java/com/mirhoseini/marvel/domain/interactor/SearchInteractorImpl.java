package com.mirhoseini.marvel.domain.interactor;


import com.mirhoseini.marvel.di.scope.Search;
import com.mirhoseini.marvel.domain.client.MarvelApi;
import com.mirhoseini.marvel.domain.model.CharactersResponse;
import com.mirhoseini.marvel.util.HashGenerator;
import com.mirhoseini.marvel.util.SchedulerProvider;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;
import rx.subjects.ReplaySubject;

/**
 * Created by Mohsen on 20/10/2016.
 */

@Search
public class SearchInteractorImpl implements SearchInteractor {

    private MarvelApi api;
    private SchedulerProvider scheduler;

    private ReplaySubject<CharactersResponse> offersSubject;
    private Subscription offersSubscription;

    @Inject
    public SearchInteractorImpl(MarvelApi api, SchedulerProvider scheduler) {
        this.api = api;
        this.scheduler = scheduler;
    }

    @Override
    public Observable<CharactersResponse> loadCharacter(String query,
                                                        String privateKey,
                                                        String publicKey,
                                                        long timestamp) {
        if (offersSubscription == null || offersSubscription.isUnsubscribed()) {
            offersSubject = ReplaySubject.create();

            String hash = HashGenerator.generate(timestamp, privateKey, publicKey);

            offersSubscription = api.getCharacters(query, publicKey, hash, timestamp)
                    .subscribeOn(scheduler.backgroundThread())
                    .observeOn(scheduler.mainThread())
                    .subscribe(offersSubject);
        }

        return offersSubject.asObservable();
    }


    @Override
    public void onDestroy() {
        if (offersSubscription != null && !offersSubscription.isUnsubscribed())
            offersSubscription.unsubscribe();
    }

}
