package com.mirhoseini.marvel.character.search;

import com.mirhoseini.marvel.domain.client.MarvelApi;
import com.mirhoseini.marvel.domain.model.CharactersResponse;
import com.mirhoseini.marvel.util.HashGenerator;
import com.mirhoseini.marvel.util.SchedulerProvider;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.SingleSubject;


/**
 * Created by Mohsen on 20/10/2016.
 */

@Search
class SearchInteractorImpl implements SearchInteractor {

    private MarvelApi api;
    private SchedulerProvider scheduler;

    private SingleSubject<CharactersResponse> characterSubject;
    private Disposable characterSubscription;

    @Inject
    SearchInteractorImpl(MarvelApi api, SchedulerProvider scheduler) {
        this.api = api;
        this.scheduler = scheduler;
    }

    @Override
    public Single<CharactersResponse> loadCharacter(String query,
                                                    String privateKey,
                                                    String publicKey,
                                                    long timestamp) {
        if (characterSubscription == null || characterSubscription.isDisposed()) {
            characterSubject = SingleSubject.create();

            // generate hash using timestamp and API keys
            String hash = HashGenerator.generate(timestamp, privateKey, publicKey);

            characterSubscription = api.getCharacters(query, publicKey, hash, timestamp)
                    .subscribeOn(scheduler.backgroundThread())
                    .subscribe(characterSubject::onSuccess);
        }

        return characterSubject.hide();
    }


    @Override
    public void unbind() {
        if (characterSubscription != null && !characterSubscription.isDisposed())
            characterSubscription.dispose();
    }
}
