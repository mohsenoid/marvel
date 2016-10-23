package com.mirhoseini.marvel.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.mirhoseini.marvel.Presentation.CachePresenter;
import com.mirhoseini.marvel.R;
import com.mirhoseini.marvel.database.model.CharacterModel;
import com.mirhoseini.marvel.di.component.ApplicationComponent;
import com.mirhoseini.marvel.di.module.AppCacheModule;
import com.mirhoseini.marvel.util.GridSpacingItemDecoration;
import com.mirhoseini.marvel.view.BaseView;
import com.mirhoseini.marvel.view.CacheView;
import com.mirhoseini.marvel.view.adapter.CharactersRecyclerViewAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.subscriptions.CompositeSubscription;
import timber.log.Timber;

/**
 * Created by Mohsen on 20/10/2016.
 */

public class CharacterCacheFragment extends BaseFragment implements CacheView {

    // injecting dependencies via Dagger
    @Inject
    public CachePresenter presenter;
    @Inject
    Context context;
    @Inject
    GridLayoutManager layoutManager;
    @Inject
    GridSpacingItemDecoration gridSpacingItemDecoration;
    @Inject
    CharactersRecyclerViewAdapter adapter;
    @Inject
    OnListFragmentInteractionListener listener;

    // injecting views via ButterKnife
    @BindView(R.id.list)
    RecyclerView list;
    @BindView(R.id.empty)
    ViewGroup empty;

    private CompositeSubscription subscriptions = new CompositeSubscription();

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public CharacterCacheFragment() {
    }

    public static CharacterCacheFragment newInstance() {
        CharacterCacheFragment fragment = new CharacterCacheFragment();
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        subscriptions.add(
                adapter.asObservable()
                        .filter(characterModel -> null != listener)
                        .subscribe(listener::showCharacter));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cache, container, false);

        ButterKnife.bind(this, view);

        initRecyclerView();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        presenter.loadLast5CharactersCachedData();
    }

    @Override
    protected void injectDependencies(ApplicationComponent component, Context context) {
        component
                .plus(new AppCacheModule(context, this, 2))
                .inject(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;

        presenter.destroy();
        presenter = null;

        subscriptions.unsubscribe();
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

    private void initRecyclerView() {
        list.setLayoutManager(layoutManager);
        list.addItemDecoration(gridSpacingItemDecoration);
    }

    @Override
    public void setLast5CharactersCachedData(List<CharacterModel> characterModels) {
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
    public void showError(Throwable throwable) {
        Timber.e(throwable, "Error!");

        Toast.makeText(context, throwable.getMessage(), Toast.LENGTH_LONG).show();
    }

    public interface OnListFragmentInteractionListener extends BaseView {

        void showCharacter(CharacterModel character);

    }
}
