package com.mirhoseini.marvel.util;

import io.reactivex.Scheduler;

/**
 * Created by Mohsen on 20/10/2016.
 */

public interface SchedulerProvider {

    Scheduler mainThread();

    Scheduler backgroundThread();

}
