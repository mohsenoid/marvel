package com.mirhoseini.marvel.character.search;

import com.mirhoseini.marvel.BuildConfig;
import com.mirhoseini.marvel.R;
import com.mirhoseini.marvel.activity.MainActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowToast;

import static com.mirhoseini.marvel.test.support.Assert.assertProgressDialogIsShown;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Mohsen on 21/10/2016.
 */

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class SearchFragmentRobolectricTest {

    private MainActivity activity;
    private SearchFragment fragment;

    @Before
    public void setUp() {
        // setup activity
        activity = Robolectric.setupActivity(MainActivity.class);
        assertNotNull(activity);

        // setup fragment
        fragment = (SearchFragment) activity.getSupportFragmentManager().findFragmentByTag(MainActivity.TAG_SEARCH_FRAGMENT);
        assertNotNull(fragment);

    }

    @Test
    public void testShowProgress() {
        fragment.showProgress();

        assertProgressDialogIsShown(R.string.please_wait);
    }

    @Test
    public void testHideProgress() {
        fragment.showProgress();
        fragment.hideProgress();

        assertProgressDialogIsShown(R.string.please_wait);
    }

    @Test
    public void testShowQueryNoResult() {
        fragment.showQueryNoResult();

        assertThat(activity.getString(R.string.no_result), equalTo(ShadowToast.getTextOfLatestToast()));
    }
}