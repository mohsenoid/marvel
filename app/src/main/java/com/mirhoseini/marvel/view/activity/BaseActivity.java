package com.mirhoseini.marvel.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.mirhoseini.marvel.MarvelApplication;
import com.mirhoseini.marvel.di.component.ApplicationComponent;

/**
 * Created by Mohsen on 20/10/2016.
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        injectDependencies(MarvelApplication.getComponent());

        // can be used for general purpose in all Activities of Application
    }

    protected abstract void injectDependencies(ApplicationComponent component);

}
