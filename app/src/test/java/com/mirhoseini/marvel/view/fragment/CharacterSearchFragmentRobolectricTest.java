package com.mirhoseini.marvel.view.fragment;

import com.mirhoseini.marvel.BuildConfig;
import com.mirhoseini.marvel.R;
import com.mirhoseini.marvel.test.support.ShadowSnackbar;
import com.mirhoseini.marvel.view.activity.MainActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowToast;

import static com.mirhoseini.marvel.test.support.Assert.assertProgressDialogIsShown;
import static com.mirhoseini.marvel.test.support.Assert.assertSnackbarIsShown;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by Mohsen on 21/10/2016.
 */

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21, shadows = {ShadowSnackbar.class})
public class CharacterSearchFragmentRobolectricTest {

    private MainActivity activity;
    private CharacterSearchFragment fragment;

    @Before
    public void setUp() throws Exception {
        // setup activity
        activity = Robolectric.setupActivity(MainActivity.class);
        assertNotNull(activity);

        // setup fragment
        fragment = (CharacterSearchFragment) activity.getSupportFragmentManager().findFragmentByTag(activity.TAG_SEARCH_FRAGMENT);
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
    public void testShowError() throws Exception {
        fragment.showError(new Throwable("unknown error"));

        assertSnackbarIsShown(R.string.retry_message);
    }

    @Test
    public void testShowRetryMessage() throws Exception {
        fragment.showRetryMessage(new Throwable("unknown error"));

        assertSnackbarIsShown(R.string.retry_message);
    }

    @Test
    public void testShowQueryError() throws Exception {
        fragment.showQueryError(new Throwable("unknown error"));

        assertSnackbarIsShown(R.string.retry_message);
    }

    @Test
    public void testShowQueryNoResult() throws Exception {
        fragment.showQueryNoResult();

        assertThat(activity.getString(R.string.no_result), equalTo(ShadowToast.getTextOfLatestToast()));
    }

    @Test
    public void testOnDestroy() throws Exception {
        fragment.onDetach();

        assertNull(fragment.listener);
        assertNull(fragment.presenter);
    }

}