package com.dagger.example;

import android.app.Activity;
import android.app.Application;

import com.dagger.example.dagger.ApplicationComponent;
import com.dagger.example.dagger.ApplicationModule;
import com.dagger.example.dagger.DaggerApplicationComponent;
import com.dagger.example.dagger.DataModule;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import timber.log.Timber;
import timber.log.Timber.DebugTree;

/**
 * Application class for the app.
 */

public class MyApplication extends Application implements HasActivityInjector {

    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

    private static ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            Timber.plant(new DebugTree());
        }

        applicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this))
                .dataModule(new DataModule())
                .build();

        applicationComponent.inject(this);

    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }

    public static ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
