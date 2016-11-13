package com.mirhoseini.marvel.activity;

import android.app.Instrumentation;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.WindowManager;

import com.mirhoseini.marvel.ApplicationTestComponent;
import com.mirhoseini.marvel.MarvelApplication;
import com.mirhoseini.marvel.MarvelTestApplication;
import com.mirhoseini.marvel.R;
import com.mirhoseini.marvel.domain.client.MarvelApi;
import com.mirhoseini.marvel.domain.model.CharactersResponse;
import com.mirhoseini.marvel.domain.model.Data;
import com.mirhoseini.marvel.domain.model.Results;
import com.mirhoseini.marvel.domain.model.Thumbnail;
import com.mirhoseini.marvel.util.Constants;

import org.hamcrest.core.IsInstanceOf;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import rx.Observable;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.mirhoseini.marvel.test.support.Matcher.childAtPosition;
import static org.hamcrest.Matchers.allOf;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

/**
 * Created by Mohsen on 21/10/2016.
 */

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    private static final String TEST_CHARACTER_NAME = "Test Name";
    private static final String TEST_CHARACTER_DESCRIPTION = "Test Description";
    private static final String TEST_CHARACTER_THUMBNAIL_PATH = "Test Thumbnail";
    private static final String TEST_CHARACTER_THUMBNAIL_EXTENSION = "Test Extension";

    @Rule
    public ActivityTestRule<MainActivity> mainActivity = new ActivityTestRule<>(
            MainActivity.class,
            true,
            // false: do not launch the activity immediately
            false);

    @Inject
    MarvelApi api;

    CharactersResponse expectedResult;

    @Before
    public void setUp() {
        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
        MarvelTestApplication app = (MarvelTestApplication) instrumentation.getTargetContext().getApplicationContext();
        ApplicationTestComponent component = (ApplicationTestComponent) MarvelApplication.getComponent();

        // inject dagger
        component.inject(this);

        // Set up the stub we want to return in the mock
        Results character = new Results();
        character.setName(TEST_CHARACTER_NAME);
        character.setDescription(TEST_CHARACTER_DESCRIPTION);
        character.setThumbnail(new Thumbnail(TEST_CHARACTER_THUMBNAIL_PATH, TEST_CHARACTER_THUMBNAIL_EXTENSION));

        Results[] characters = new Results[1];
        characters[0] = character;

        Data charactersData = new Data();
        charactersData.setCount(1);
        charactersData.setResults(characters);

        // put the test character in a test api result
        expectedResult = new CharactersResponse();
        expectedResult.setData(charactersData);
        expectedResult.setCode(Constants.CODE_OK);

        // Set up the mock
        when(api.getCharacters(eq(TEST_CHARACTER_NAME), any(String.class), any(String.class), any(Long.class)))
                .thenReturn(Observable.just(expectedResult));
    }

    public void unlockScreen() {
        final MainActivity activity = mainActivity.getActivity();
        Runnable wakeUpDevice = () -> activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON |
                WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED |
                WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        activity.runOnUiThread(wakeUpDevice);
    }

    @Test
    public void shouldBeAbleToSearchForTestCharacter() {
        // Launch the activity
        mainActivity.launchActivity(new Intent());

        // fix Travis locked device issue
        unlockScreen();

        // search for Test Character
        ViewInteraction appCompatAutoCompleteTextView = onView(
                allOf(withId(R.id.character), isDisplayed()));
        appCompatAutoCompleteTextView.perform(replaceText(TEST_CHARACTER_NAME));

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.show), isDisplayed()));
        appCompatButton.perform(click());

        // Check view is loaded as we expect it to be
        onView(withText(TEST_CHARACTER_NAME)).check(matches(withText(TEST_CHARACTER_NAME)));
        onView(withId(R.id.description)).check(matches(withText(TEST_CHARACTER_DESCRIPTION)));
    }

    @Test
    public void shouldBeAbleToCacheTestCharacter() {
        // Launch the activity
        mainActivity.launchActivity(new Intent());

        // search for Test Character
        ViewInteraction appCompatAutoCompleteTextView = onView(
                allOf(withId(R.id.character), isDisplayed()));
        appCompatAutoCompleteTextView.perform(replaceText(TEST_CHARACTER_NAME));

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.show), isDisplayed()));
        appCompatButton.perform(click());

        pressBack();

        // Check view is loaded as we expect it to be
        ViewInteraction cachedName = onView(
                allOf(withId(R.id.name), withText(TEST_CHARACTER_NAME),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.instanceOf(android.widget.FrameLayout.class),
                                        0),
                                1),
                        isDisplayed()));
        cachedName.check(matches(withText(TEST_CHARACTER_NAME)));
    }
}

