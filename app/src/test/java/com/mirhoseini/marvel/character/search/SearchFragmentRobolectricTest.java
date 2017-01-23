package com.mirhoseini.marvel.character.search;

import com.mirhoseini.marvel.BuildConfig;
import com.mirhoseini.marvel.R;
import com.mirhoseini.marvel.main.MainActivity;
import com.mirhoseini.marvel.test.support.ShadowSnackbar;

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
@Config(constants = BuildConfig.class, sdk = 21, shadows = {ShadowSnackbar.class})
public class SearchFragmentRobolectricTest {

    private MainActivity activity;
    private SearchFragment fragment;

    @Before
    public void setUp() throws Exception {
        // setup activity
        activity = Robolectric.setupActivity(MainActivity.class);
        assertNotNull(activity);

        // setup fragment
        fragment = (SearchFragment) activity.getSupportFragmentManager().findFragmentByTag(MainActivity.TAG_SEARCH_FRAGMENT);
        assertNotNull(fragment);

    }

    @Test
    public void testShowProgress() throws Exception {
        fragment.showProgress();

        assertProgressDialogIsShown(R.string.please_wait);
    }

    @Test
    public void testHideProgress() throws Exception {
        fragment.showProgress();
        fragment.hideProgress();

        assertProgressDialogIsShown(R.string.please_wait);
    }

    @Test
    public void testShowQueryNoResult() throws Exception {
        fragment.showQueryNoResult();

        assertThat(activity.getString(R.string.no_result), equalTo(ShadowToast.getTextOfLatestToast()));
    }

}