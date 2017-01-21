package com.mirhoseini.marvel.character.search;

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
import com.mirhoseini.marvel.MarvelApplication;
import com.mirhoseini.marvel.R;
import com.mirhoseini.marvel.base.BaseFragment;
import com.mirhoseini.marvel.database.model.CharacterModel;
import com.mirhoseini.utils.Utils;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnEditorAction;
import rx.Observable;
import rx.subjects.PublishSubject;
import timber.log.Timber;

/**
 * Created by Mohsen on 20/10/2016.
 */

public class SearchFragment extends BaseFragment implements SearchContract.View {

    // injecting dependencies via Dagger
    @Inject
    Context context;
    @Inject
    Resources resources;
    @Inject
    FirebaseAnalytics firebaseAnalytics;
    @Inject
    SearchContract.Presenter presenter;

    // injecting views via ButterKnife
    @BindView(R.id.character)
    AutoCompleteTextView character;
    @BindView(R.id.show)
    Button show;

    ProgressDialog progressDialog;

    PublishSubject<CharacterModel> notifyCharacter = PublishSubject.create();
    PublishSubject<String> notifyMessage = PublishSubject.create();
    PublishSubject<Boolean> notifyOffline = PublishSubject.create();

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public SearchFragment() {
    }

    public static SearchFragment newInstance() {
        SearchFragment fragment = new SearchFragment();
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
            logFirebaseAnalyticsSearchEvent(query);

            presenter.doSearch(Utils.isConnected(context), query, Utils.getCurrentTimestamp());
        } else {
            character.setError(resources.getString(R.string.character_error));
            character.requestFocus();
        }
    }

    private void logFirebaseAnalyticsSearchEvent(String query) {
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.SEARCH_TERM, query);
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SEARCH, bundle);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        presenter.bind(this);
    }

    @Override
    protected void injectDependencies(MarvelApplication application) {
        application
                .getSearchSubComponent()
                .inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        ButterKnife.bind(this, view);

        return view;
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
    public void onDetach() {
        super.onDetach();

        presenter.unbind();
    }

    @Override
    public void showMessage(String message) {
        notifyMessage.onNext(message);
    }

    @Override
    public void showOfflineMessage(boolean isCritical) {
        notifyOffline.onNext(isCritical);
    }

    @Override
    public void showError(Throwable throwable) {
        Timber.e(throwable, "Error!");

        showRetryMessage(throwable);
    }

    @Override
    public void showDatabaseError(Throwable throwable) {
        Timber.e(throwable, "Error!");

        showMessage(throwable.getMessage());
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
    public void showServiceError(ApiResponseCodeException throwable) {
        Timber.e(throwable, "Service Error!");

        showRetryMessage(throwable);
    }


    @Override
    public void showQueryError(Throwable throwable) {
        Timber.e(throwable, "Query error!");

        showRetryMessage(throwable);
    }

    @Override
    public void showCharacter(CharacterModel character) {
        logFirebaseAnalyticsSelectEvent(character);

        notifyCharacter.onNext(character);
    }

    private void logFirebaseAnalyticsSelectEvent(CharacterModel character) {
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, character.getId() + "");
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, character.getName());
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);
    }

    public Observable<CharacterModel> characterObservable() {
        return notifyCharacter.asObservable();
    }

    public Observable<String> messageObservable() {
        return notifyMessage.asObservable();
    }

    public Observable<Boolean> offlineObservable() {
        return notifyOffline.asObservable();
    }

}
