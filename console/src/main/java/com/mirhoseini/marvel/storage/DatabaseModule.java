package com.mirhoseini.marvel.storage;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Mohsen on 20/10/2016.
 */

@Module
public class DatabaseModule {

    @Provides
    @Singleton
    DatabaseHelper provideDatabaseHelperService() {
        return new DatabaseHelperImpl();
    }

}
