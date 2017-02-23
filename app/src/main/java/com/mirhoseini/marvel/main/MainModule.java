package com.mirhoseini.marvel.main;

import android.app.Activity;
import android.content.Context;

import com.mirhoseini.marvel.ActivityContext;
import com.mirhoseini.marvel.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Mohsen on 22/01/2017.
 */
@Module
public class MainModule {
    private Activity activity;

    MainModule(Activity activity) {
        this.activity = activity;
    }

    @Provides
    @PerActivity
    @ActivityContext
    Context provideActivityContext() {
        return activity;
    }

    @Provides
    @PerActivity
    Activity provideActivity() {
        return activity;
    }

}
