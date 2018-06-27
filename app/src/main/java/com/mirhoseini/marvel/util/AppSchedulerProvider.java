package com.mirhoseini.marvel.util;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Mohsen on 20/10/2016.
 */

public class AppSchedulerProvider implements SchedulerProvider {

    @Inject
    public AppSchedulerProvider() {
    }

    @Override
    public Scheduler mainThread() {
        return AndroidSchedulers.mainThread();
    }

    @Override
    public Scheduler backgroundThread() {
        return Schedulers.io();
    }

}
