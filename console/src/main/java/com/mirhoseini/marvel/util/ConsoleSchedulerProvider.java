package com.mirhoseini.marvel.util;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by Mohsen on 20/10/2016.
 */

public class ConsoleSchedulerProvider implements SchedulerProvider {

    @Inject
    public ConsoleSchedulerProvider() {
    }

    @Override
    public Scheduler mainThread() {
        return Schedulers.trampoline();
    }

    @Override
    public Scheduler backgroundThread() {
        return Schedulers.trampoline();
    }

}
