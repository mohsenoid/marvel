package com.mirhoseini.marvel.base;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.mirhoseini.marvel.ApplicationComponent;
import com.mirhoseini.marvel.MarvelApplication;

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