package com.mirhoseini.marvel;

import com.mirhoseini.marvel.util.ConsoleConstants;
import com.mirhoseini.marvel.util.ConsoleSchedulerProvider;
import com.mirhoseini.marvel.util.Constants;
import com.mirhoseini.marvel.util.SchedulerProvider;
import com.mirhoseini.marvel.util.StateManager;
import com.mirhoseini.marvel.util.StateManagerImpl;

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
        return ConsoleConstants.NETWORK_CONNECTION_TIMEOUT;
    }

    @Provides
    @Singleton
    HttpUrl provideEndpoint() {
        return HttpUrl.parse(ConsoleConstants.BASE_URL);
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
        return ConsoleConstants.CACHE_SIZE;
    }

    @Provides
    @Singleton
    @Named("cacheMaxAge")
    int provideCacheMaxAgeMinutes() {
        return ConsoleConstants.CACHE_MAX_AGE;
    }

    @Provides
    @Singleton
    @Named("cacheMaxStale")
    int provideCacheMaxStaleDays() {
        return ConsoleConstants.CACHE_MAX_STALE;
    }

    @Provides
    @Singleton
    @Named("retryCount")
    public int provideApiRetryCount() {
        return ConsoleConstants.API_RETRY_COUNT;
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

    @Provides
    @Singleton
    public StateManager provideStateManager(StateManagerImpl stateManager) {
        return stateManager;
    }
}
