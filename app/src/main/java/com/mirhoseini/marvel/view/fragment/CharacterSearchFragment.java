package com.mirhoseini.marvel.view.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.mirhoseini.marvel.Presentation.SearchPresenter;
import com.mirhoseini.marvel.R;
import com.mirhoseini.marvel.database.model.CharacterModel;
import com.mirhoseini.marvel.di.component.ApplicationComponent;
import com.mirhoseini.marvel.di.module.AppSearchModule;
import com.mirhoseini.marvel.view.BaseView;
import com.mirhoseini.marvel.view.SearchView;
import com.mirhoseini.utils.Utils;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnEditorAction;
import timber.log.Timber;

/**
 * Created by Mohsen on 20/10/2016.
 */

public class CharacterSearchFragment extends BaseFragment implements SearchView {

    @Inject
    Context context;
    @Inject
    Resources resources;
    @Inject
    SearchPresenter presenter;
    @Inject
    OnListFragmentInteractionListener listener;
    @BindView(R.id.character)
    AutoCompleteTextView character;
    @BindView(R.id.show)
    Button show;
    private FirebaseAnalytics firebaseAnalytics;
    private ProgressDialog progressDialog;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public CharacterSearchFragment() {
    }

    public static CharacterSearchFragment newInstance() {
        CharacterSearchFragment fragment = new CharacterSearchFragment();
        return fragment;
    }

    @OnEditorAction(R.id.character)
    boolean onEditorAction(EditText v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH || (event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
            onShowClick(v);
            return true;
        }
        return false;
    }

    @OnClick(R.id.show)
    void onShowClick(View view) {
        character.setError(null);
        Utils.hideKeyboard(context, character);

        String query = character.getText().toString().trim();

        if (presenter.isQueryValid(query)) {
            logFirebaseAnalyticsEvent(query);

            presenter.doSearch(Utils.isConnected(context), query, Utils.getCurrentTimestamp());
        } else {
            character.setError(resources.getString(R.string.character_error));
            character.requestFocus();
        }
    }

    private void logFirebaseAnalyticsEvent(String query) {
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.SEARCH_TERM, query);
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SEARCH, bundle);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // Obtain the FirebaseAnalytics instance.
        firebaseAnalytics = FirebaseAnalytics.getInstance(context);
    }

    @Override
    public void onResume() {
        super.onResume();

        presenter.loadCharactersCachedData();
    }

    @Override
    public void setCharactersCachedData(List<CharacterModel> characters) {
        character.setAdapter(new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, characters));
    }

    @Override
    protected void injectDependencies(ApplicationComponent component, Context context) {
        component
                .plus(new AppSearchModule(context, this))
                .inject(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;

        presenter.destroy();
        presenter = null;
    }

    @Override
    public void showMessage(String message) {
        if (null != listener) {
            listener.showMessage(message);
        }
    }

    @Override
    public void showOfflineMessage() {
        if (null != listener) {
            listener.showOfflineMessage();
        }
    }

    @Override
    public void showError(Throwable throwable) {
        Timber.e(throwable, "Error!");

        showRetryMessage(throwable);
    }

    @Override
    public void showRetryMessage(Throwable throwable) {
        Timber.e(throwable, "Retry error!");

        Snackbar.make(character, resources.getString(R.string.retry_message), Snackbar.LENGTH_LONG)
                .setAction(R.string.retry, this::onShowClick)
                .show();
    }

    @Override
    public void showQueryNoResult() {
        showMessage(resources.getString(R.string.no_result));
    }

    @Override
    public void showProgress() {
        if (null != progressDialog)
            progressDialog.dismiss();

        progressDialog = new ProgressDialog(getActivity());
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
        if (null != listener)
            listener.showCharacter(character);
    }

    public interface OnListFragmentInteractionListener extends BaseView {

        void showCharacter(CharacterModel character);

    }

}
