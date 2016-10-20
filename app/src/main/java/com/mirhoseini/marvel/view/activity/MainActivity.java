package com.mirhoseini.marvel.view.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.mirhoseini.marvel.R;
import com.mirhoseini.marvel.database.model.CharacterModel;
import com.mirhoseini.marvel.di.component.ApplicationComponent;
import com.mirhoseini.marvel.view.BaseView;
import com.mirhoseini.marvel.view.fragment.CharacterCacheFragment;
import com.mirhoseini.marvel.view.fragment.CharacterSearchFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

/**
 * Created by Mohsen on 20/10/2016.
 */

public class MainActivity extends BaseActivity implements CharacterSearchFragment.OnListFragmentInteractionListener, CharacterCacheFragment.OnListFragmentInteractionListener, BaseView {

    private static final String TAG_SEARCH_FRAGMENT = "search_fragment";
    private static final String TAG_CACHE_FRAGMENT = "cache_fragment";

    // injecting dependencies via Dagger
    @Inject
    Context context;
    @Inject
    Resources resources;

    // injecting views via ButterKnife
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private CharacterSearchFragment searchFragment;
    private CharacterCacheFragment cacheFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // inject views using ButterKnife
        ButterKnife.bind(this);

        setupToolbar();

        if (null == savedInstanceState) {
            searchFragment = CharacterSearchFragment.newInstance();
            cacheFragment = CharacterCacheFragment.newInstance();
            attachFragments();
        } else {
            searchFragment = (CharacterSearchFragment) getSupportFragmentManager().findFragmentByTag(TAG_SEARCH_FRAGMENT);
            cacheFragment = (CharacterCacheFragment) getSupportFragmentManager().findFragmentByTag(TAG_CACHE_FRAGMENT);
        }

        Timber.d("Main Activity Created");
    }

    @Override
    protected void injectDependencies(ApplicationComponent component) {
        component.inject(this);
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.logo);
        getSupportActionBar().setTitle(R.string.main_title);
    }

    private void attachFragments() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.search_fragment, searchFragment, TAG_SEARCH_FRAGMENT);
        fragmentTransaction.replace(R.id.cache_fragment, cacheFragment, TAG_CACHE_FRAGMENT);
        fragmentTransaction.commitAllowingStateLoss();
    }

    @Override
    public void showMessage(String message) {
        Timber.d("Showing Message: %s", message);

        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showOfflineMessage() {
        Timber.d("Showing Offline Message");

        Snackbar.make(toolbar, R.string.offline_message, Snackbar.LENGTH_LONG)
                .setAction(R.string.go_online, v -> {
                    startActivity(new Intent(
                            Settings.ACTION_WIFI_SETTINGS));
                })
                .setActionTextColor(Color.GREEN)
                .show();
    }

    @Override
    public void showCharacter(CharacterModel character) {
        startActivity(CharacterActivity.newIntent(this, character));
    }
}
