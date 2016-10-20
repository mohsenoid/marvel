package com.mirhoseini.marvel.view.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mirhoseini.marvel.Presentation.MainPresenter;
import com.mirhoseini.marvel.R;
import com.mirhoseini.marvel.database.model.CharacterModel;
import com.mirhoseini.marvel.di.component.ApplicationComponent;
import com.mirhoseini.marvel.di.module.MainModule;
import com.mirhoseini.marvel.util.GridSpacingItemDecoration;
import com.mirhoseini.marvel.view.MainView;
import com.mirhoseini.marvel.view.adapter.CharactersRecyclerViewAdapter;
import com.mirhoseini.utils.Utils;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnEditorAction;
import rx.subscriptions.CompositeSubscription;
import timber.log.Timber;

/**
 * Created by Mohsen on 20/10/2016.
 */

public class MainActivity extends BaseActivity implements MainView {

    // injecting dependencies via Dagger
    @Inject
    Context context;
    @Inject
    Resources resources;
    @Inject
    MainPresenter presenter;
    @Inject
    CharactersRecyclerViewAdapter adapter;
    //    @Inject
    GridLayoutManager layoutManager;


    // injecting views via ButterKnife
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.character)
    AutoCompleteTextView character;
    @BindView(R.id.show)
    Button show;
    @BindView(R.id.list)
    RecyclerView list;
    @BindView(R.id.empty)
    ViewGroup empty;

    private ProgressDialog progressDialog;
    private CompositeSubscription subscriptions = new CompositeSubscription();

    @OnEditorAction(R.id.character)
    boolean onEditorAction(EditText v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH || event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
            onShowClick(v);
            return true;
        }
        return false;
    }

    @OnClick(R.id.show)
    void onShowClick(View view) {
        character.setError(null);
        Utils.hideKeyboard(this, character);

        String query = character.getText().toString().trim();

        if (presenter.isQueryValid(query)) {
            presenter.doSearch(Utils.isConnected(context), query, Utils.getCurrentTimestamp());
        } else {
            character.setError(resources.getString(R.string.character_error));
            character.requestFocus();
        }
    }

    AlertDialog internetConnectionDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // inject views using ButterKnife
        ButterKnife.bind(this);

        setupToolbar();

        subscriptions.add(
                adapter.asObservable()
//                        .filter(characterModel -> null != listener)
                        .subscribe(this::showCharacter)
        );

        layoutManager = new GridLayoutManager(context, 2);
        // Create a custom SpanSizeLookup where the first item spans both columns
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return position == 0 ? 2 : 1;
            }
        });
        list.setLayoutManager(layoutManager);
        list.addItemDecoration(new GridSpacingItemDecoration(2, 48, true, 1));

        Timber.d("Main Activity Created");
    }

    @Override
    protected void injectDependencies(ApplicationComponent component) {
        component
                .plus(new MainModule(this))
                .inject(this);
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.logo);
        getSupportActionBar().setTitle(R.string.main_title);
    }

    @Override
    protected void onResume() {
        super.onResume();

        presenter.loadCharactersCacheData();
        presenter.loadLast5CharactersCacheData();

        Timber.d("Main Activity Resumed");
    }

    @Override
    public void setCharactersCacheData(List<CharacterModel> characters) {
        character.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, characters));
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
    public void showNetworkConnectionError(boolean isForce) {
        Timber.d("Showing Network Connection Error Message");

        hideInternetConnectionError();
        internetConnectionDialog = Utils.showNoInternetConnectionDialog(this, isForce);
    }

    public void hideInternetConnectionError() {
        if (internetConnectionDialog != null)
            internetConnectionDialog.dismiss();
    }

    @Override
    public void showProgress() {
        if (null != progressDialog)
            progressDialog.dismiss();

        progressDialog = new ProgressDialog(this);
        progressDialog.setIndeterminate(true);
        progressDialog.setTitle(R.string.please_wait);
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        if (null != progressDialog)
            progressDialog.dismiss();
    }

    @Override
    public void showQueryError(Throwable throwable) {
        Timber.e(throwable, "Query error!");

        showRetryMessage(throwable);
    }

    @Override
    public void showCharacter(CharacterModel character) {
        startActivity(CharacterActivity.newIntent(this, character));
    }

    @Override
    public void showRetryMessage(Throwable throwable) {
        String errorMessage;

        if (null != throwable)
            errorMessage = throwable.getMessage();
        else
            errorMessage = resources.getString(R.string.retry_message);

        Snackbar.make(character, errorMessage, Snackbar.LENGTH_LONG)
                .setAction(R.string.retry, this::onShowClick)
                .show();
    }

    @Override
    public void showQueryNoResult() {
        showMessage(resources.getString(R.string.no_result));
    }

    @Override
    public void setLast5CharactersCacheData(List<CharacterModel> characterModels) {
        if (characterModels.size() > 0) {
            list.setVisibility(View.VISIBLE);
            empty.setVisibility(View.GONE);

            adapter.setCharacters(characterModels);
            list.setAdapter(adapter);
        } else {
            list.setVisibility(View.GONE);
            empty.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        subscriptions.unsubscribe();
    }
}
