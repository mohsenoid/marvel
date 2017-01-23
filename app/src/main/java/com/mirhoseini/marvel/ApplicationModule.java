package com.mirhoseini.marvel;

import android.content.Context;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.mirhoseini.marvel.util.AppConstants;
import com.mirhoseini.marvel.util.AppSchedulerProvider;
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
public class ApplicationModule {

    @Provides
    @Singleton
    @Named("isDebug")
    boolean provideIsDebug() {
        return BuildConfig.DEBUG;
    }

    @Provides
    @Singleton
    StateManager provideStateManager(StateManagerImpl stateManager) {
        return stateManager;
    }

    @Provides
    FirebaseAnalytics provideFirebaseAnalytics(@ApplicationContext Context context) {
        return FirebaseAnalytics.getInstance(context);
    }

    @Provides
    @Singleton
    SchedulerProvider provideAppScheduler() {
        return new AppSchedulerProvider();
    }

    @Provides
    @Singleton
    @Named("networkTimeoutInSeconds")
    int provideNetworkTimeoutInSeconds() {
        return AppConstants.NETWORK_CONNECTION_TIMEOUT;
    }

    @Provides
    @Singleton
    @Named("cacheMaxAge")
    int provideCacheMaxAgeMinutes() {
        return AppConstants.CACHE_MAX_AGE;
    }

    @Provides
    @Singleton
    @Named("cacheMaxStale")
    int provideCacheMaxStaleDays() {
        return AppConstants.CACHE_MAX_STALE;
    }

    @Provides
    @Singleton
    @Named("retryCount")
    int provideApiRetryCount() {
        return AppConstants.API_RETRY_COUNT;
    }

    @Provides
    @Singleton
    @Named("cacheDir")
    File provideCacheDir(@ApplicationContext Context context) {
        return context.getCacheDir();
    }

    @Provides
    @Singleton
    @Named("cacheSize")
    long provideCacheSize() {
        return AppConstants.CACHE_SIZE;
    }

    @Provides
    @Singleton
    HttpUrl provideEndpoint() {
        return HttpUrl.parse(Constants.BASE_URL);
    }

}
