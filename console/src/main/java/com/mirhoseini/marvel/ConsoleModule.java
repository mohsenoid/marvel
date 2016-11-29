package com.mirhoseini.marvel;

import com.mirhoseini.marvel.util.ConsoleSchedulerProvider;
import com.mirhoseini.marvel.util.Constants;
import com.mirhoseini.marvel.util.SchedulerProvider;

import java.io.File;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;

/**
 * Created by Mohsen on 20/10/2016.
 */

@Module
public class ConsoleModule {

    @Provides
    @Singleton
    @Named("isDebug")
    boolean provideIsDebug() {
        return false;
    }

    @Provides
    @Singleton
    @Named("networkTimeoutInSeconds")
    int provideNetworkTimeoutInSeconds() {
        return Constants.NETWORK_CONNECTION_TIMEOUT;
    }

    @Provides
    @Singleton
    HttpUrl provideEndpoint() {
        return HttpUrl.parse(Constants.BASE_URL);
    }

    @Provides
    @Singleton
    SchedulerProvider provideAppScheduler() {
        return new ConsoleSchedulerProvider();
    }

    @Provides
    @Singleton
    @Named("cacheSize")
    long provideCacheSize() {
        return Constants.CACHE_SIZE;
    }

    @Provides
    @Singleton
    @Named("cacheMaxAge")
    int provideCacheMaxAgeMinutes() {
        return Constants.CACHE_MAX_AGE;
    }

    @Provides
    @Singleton
    @Named("cacheMaxStale")
    int provideCacheMaxStaleDays() {
        return Constants.CACHE_MAX_STALE;
    }

    @Provides
    @Singleton
    @Named("cacheDir")
    File provideCacheDir() {
        return new File("build");
    }

    @Provides
    @Named("isConnect")
    boolean provideIsConnect() {
        return true;
    }

}
