package com.mirhoseini.marvel.domain.interactor;

import com.mirhoseini.marvel.domain.client.MarvelApi;
import com.mirhoseini.marvel.domain.model.CharactersResponse;
import com.mirhoseini.marvel.domain.model.Data;
import com.mirhoseini.marvel.domain.model.Results;
import com.mirhoseini.marvel.util.SchedulerProvider;

import org.junit.Before;
import org.junit.Test;

import java.util.Collections;

import rx.Observable;
import rx.observers.TestSubscriber;
import rx.schedulers.Schedulers;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Mohsen on 21/10/2016.
 */

public class SearchInteractorTest {

    SearchInteractor interactor;
    MarvelApi api;
    SchedulerProvider scheduler;
    CharactersResponse expectedResult;

    @Before
    public void setup() {
        api = mock(MarvelApi.class);
        scheduler = mock(SchedulerProvider.class);

        // Set up the stub we want to return in the mock
        Results character = new Results();
        character.setName("Test Character");

        Results[] characters = new Results[1];
        characters[0] = character;

        Data charactersData = new Data();
        charactersData.setResults(characters);

        // put the test offer in a test api result
        expectedResult = new CharactersResponse();
        expectedResult.setData(charactersData);

        // mock scheduler to run immediately
        when(scheduler.mainThread())
                .thenReturn(Schedulers.immediate());
        when(scheduler.backgroundThread())
                .thenReturn(Schedulers.immediate());

        // mock api result with expected result
        when(api.getCharacters(any(String.class), any(String.class), any(String.class), any(Long.class)))
                .thenReturn(Observable.just(expectedResult));

        // create a real new interactor using mocked api and scheduler
        interactor = new SearchInteractorImpl(api, scheduler);
    }

    @Test
    public void testLoadOffers() throws Exception {
        TestSubscriber<CharactersResponse> testSubscriber = new TestSubscriber<>();

        // call interactor with some random params
        interactor.loadCharacter("query", "privateKey", "publicKey", 1)
                .subscribe(testSubscriber);

        // it must return the expectedResult with no error
        testSubscriber.assertNoErrors();
        testSubscriber.assertReceivedOnNext(Collections.singletonList(expectedResult));
    }
}
