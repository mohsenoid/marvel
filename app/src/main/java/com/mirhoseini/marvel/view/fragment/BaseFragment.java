package com.mirhoseini.marvel.view.fragment;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.mirhoseini.marvel.MarvelApplication;
import com.mirhoseini.marvel.di.component.ApplicationComponent;

/**
 * Created by Mohsen on 20/10/2016.
 */

public abstract class BaseFragment extends Fragment {

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        injectDependencies(MarvelApplication.getComponent(), context);

        // can be used for general purpose in all Fragments of Application
    }

    protected abstract void injectDependencies(ApplicationComponent component, Context context);

}