package com.mirhoseini.marvel.activity;

import com.mirhoseini.marvel.BuildConfig;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowToast;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Mohsen on 21/10/2016.
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class MainActivityRobolectricTest {

    private final static String TEST_TEXT = "This is a test text.";
    private MainActivity activity;

    @Before
    public void setUp() {
        activity = Robolectric.setupActivity(MainActivity.class);

        assertNotNull(activity);
    }

    @Test
    public void testShowToastMessage() {
        activity.showMessage(TEST_TEXT);

        assertThat(TEST_TEXT, equalTo(ShadowToast.getTextOfLatestToast()));
    }
}
