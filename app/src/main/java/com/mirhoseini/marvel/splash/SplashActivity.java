package com.mirhoseini.marvel.splash;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;

import com.mirhoseini.marvel.ApplicationComponent;
import com.mirhoseini.marvel.ApplicationContext;
import com.mirhoseini.marvel.MarvelApplication;
import com.mirhoseini.marvel.R;
import com.mirhoseini.marvel.base.BaseActivity;
import com.mirhoseini.marvel.main.MainActivity;
import com.mirhoseini.marvel.util.AppConstants;

import javax.inject.Inject;

import butterknife.ButterKnife;
import timber.log.Timber;

/**
 * Created by Mohsen on 20/10/2016.
 */

public class SplashActivity extends BaseActivity {

    // injecting dependencies via Dagger
    @Inject
    @ApplicationContext
    Context applicationContext;

    // Thread to process splash screen events
    private Thread splashThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ButterKnife.bind(this);

        // The thread to wait for splash screen events
        splashThread = new Thread() {
            @Override
            public void run() {

                try {
                    synchronized (this) {
                        // Wait given period of time or exit on touch
                        wait(AppConstants.SPLASH_TIMEOUT_SEC);
                    }
                } catch (InterruptedException ex) {
                    Timber.e(ex, "Splash thread interrupted!");
                }

                finish();

                // Open MainActivity
                Intent mainActivityIntent = new Intent();
                mainActivityIntent.setClass(applicationContext, MainActivity.class);
                startActivity(mainActivityIntent);
            }
        };

        splashThread.start();
    }

    @Override
    protected void injectDependencies(MarvelApplication application, ApplicationComponent component) {
        component.inject(this);
    }

    // Listening to whole activity touch events
    @Override
    public boolean onTouchEvent(MotionEvent evt) {
        if (evt.getAction() == MotionEvent.ACTION_DOWN) {
            synchronized (splashThread) {
                splashThread.notifyAll();
            }
        }

        return true;
    }

    @Override
    protected void releaseSubComponents(MarvelApplication application) {

    }

}