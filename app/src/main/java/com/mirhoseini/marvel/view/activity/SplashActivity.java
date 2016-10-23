package com.mirhoseini.marvel.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;

import com.mirhoseini.marvel.R;
import com.mirhoseini.marvel.di.component.ApplicationComponent;
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
    Context context;

    // The thread to process splash screen events
    private Thread splashThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ButterKnife.bind(this);

        // The Theme's windowBackground is masked by the opaque background of the activity, and
        // the windowBackground causes an unnecessary overdraw. Nullifying the windowBackground
        // removes that overdraw.
        getWindow().setBackgroundDrawable(null);

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
                mainActivityIntent.setClass(context, MainActivity.class);
                startActivity(mainActivityIntent);
            }
        };

        splashThread.start();
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
    protected void injectDependencies(ApplicationComponent component) {
        component.inject(this);
    }
}