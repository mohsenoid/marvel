package com.mirhoseini.marvel;

import timber.log.Timber;

/**
 * Created by Mohsen on 20/10/2016.
 */

public class MarvelApplicationImpl extends MarvelApplication {

    @Override
    public void initApplication() {

        // initialize Timber in debug version to log
        Timber.plant(new Timber.DebugTree() {
            @Override
            protected String createStackElementTag(StackTraceElement element) {
                //adding line number to logs
                return super.createStackElementTag(element) + ":" + element.getLineNumber();
            }
        });

    }
}
