package com.mirhoseini.marvel.character.search;

import com.mirhoseini.marvel.database.DatabaseHelper;
import com.mirhoseini.marvel.database.mapper.Mapper;
import com.mirhoseini.marvel.database.model.CharacterModel;
import com.mirhoseini.marvel.domain.client.MarvelApi;
import com.mirhoseini.marvel.domain.model.CharactersResponse;
import com.mirhoseini.marvel.domain.model.Data;
import com.mirhoseini.marvel.domain.model.Results;
import com.mirhoseini.marvel.domain.model.Thumbnail;
import com.mirhoseini.marvel.util.Constants;
import com.mirhoseini.marvel.util.SchedulerProvider;

import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
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

    private static final String TEST_CHARACTER_NAME = "Test Name";
    private static final String TEST_CHARACTER_DESCRIPTION = "Test Description";
    private static final String TEST_CHARACTER_THUMBNAIL_PATH = "Test Thumbnail";
    private static final String TEST_CHARACTER_THUMBNAIL_EXTENSION = "Test Extension";

    SearchInteractor interactor;
    MarvelApi api;
    SchedulerProvider scheduler;
    DatabaseHelper database;
    CharacterModel expectedResult;

    @Before
    public void setup() {
        api = mock(MarvelApi.class);
        scheduler = mock(SchedulerProvider.class);
        database = mock(DatabaseHelper.class);

        // Set up the stub we want to return in the mock
        Results character = new Results();
        character.setName(TEST_CHARACTER_NAME);
        character.setDescription(TEST_CHARACTER_DESCRIPTION);
        character.setThumbnail(new Thumbnail(TEST_CHARACTER_THUMBNAIL_PATH, TEST_CHARACTER_THUMBNAIL_EXTENSION));

        Results[] characters = new Results[1];
        characters[0] = character;

        Data charactersData = new Data();
        charactersData.setResults(characters);
        charactersData.setCount(1);

        // put the test character in a test api result
        CharactersResponse response = new CharactersResponse();
        response.setCode(Constants.CODE_OK);
        response.setData(charactersData);

        expectedResult = Mapper.mapCharacterResponseToCharacter(response);

        // mock scheduler to run immediately
        when(scheduler.mainThread())
                .thenReturn(Schedulers.immediate());
        when(scheduler.backgroundThread())
                .thenReturn(Schedulers.immediate());

        // mock api result with expected result
        when(api.getCharacters(any(String.class), any(String.class), any(String.class), any(Long.class)))
                .thenReturn(Observable.just(response));

        try {
            when(database.getCharacters(any(String.class)))
                    .thenReturn(null);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // create a real new interactor using mocked api and scheduler
        interactor = new SearchInteractor(api, scheduler, database);
    }

    @Test
    public void testLoadCharacters() throws Exception {
        TestSubscriber<CharacterModel> testSubscriber = new TestSubscriber<>();

        // call interactor with some random params
        interactor.loadCharacter("query", 1)
                .subscribe(testSubscriber);

        // it must return the expectedResult with no error
        testSubscriber.assertNoErrors();
    }
}
