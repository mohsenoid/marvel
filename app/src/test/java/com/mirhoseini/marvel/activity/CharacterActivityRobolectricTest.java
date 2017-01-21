package com.mirhoseini.marvel.activity;

import android.content.Intent;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mirhoseini.marvel.BuildConfig;
import com.mirhoseini.marvel.R;
import com.mirhoseini.marvel.database.model.CharacterModel;
import com.mirhoseini.marvel.test.support.ShadowSnackbar;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by Mohsen on 13/11/2016.
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21, shadows = {ShadowSnackbar.class})
public class CharacterActivityRobolectricTest {

    static final String TEST_CHARACTER_NAME = "Test Name";
    static final String TEST_CHARACTER_DESCRIPTION = "Test Description";
    static final String TEST_CHARACTER_THUMBNAIL = "Test Thumbnail";

    CharacterActivity activity;
    CharacterModel character;

    @Before
    public void setUp() throws Exception {
        // Set up the stub we want to return in the mock
        character = new CharacterModel(0, TEST_CHARACTER_NAME, TEST_CHARACTER_DESCRIPTION, TEST_CHARACTER_THUMBNAIL);

        Intent intent = CharacterActivity.newIntent(RuntimeEnvironment.application, character);

        activity = Robolectric.buildActivity(CharacterActivity.class)
                .withIntent(intent).create().get();
        assertNotNull(activity);
    }

    @Test
    public void viewFunctionality() throws Exception {
        TextView title = (TextView) ((ViewGroup) activity.findViewById(R.id.toolbar)).getChildAt(0);
        assertTrue(title.getText().toString().equals(TEST_CHARACTER_NAME));
    }

    @Test
    public void finishIfNoCharacter() throws Exception {
        Intent intent = CharacterActivity.newIntent(RuntimeEnvironment.application, null);

        activity = Robolectric.buildActivity(CharacterActivity.class)
                .withIntent(intent).create().get();

        assertTrue(activity.isFinishing());
    }

}