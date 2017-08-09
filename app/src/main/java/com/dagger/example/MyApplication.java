package com.dagger.example;

import android.app.Activity;
import android.app.Application;

import com.dagger.example.dagger.components.ApplicationComponent;
import com.dagger.example.dagger.components.DaggerApplicationComponent;
import com.dagger.example.dagger.modules.ApplicationModule;
import com.dagger.example.dagger.modules.DataModule;
import com.facebook.stetho.Stetho;
import com.uphyca.stetho_realm.RealmInspectorModulesProvider;

import java.util.regex.Pattern;

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

        setupStetho();

        applicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this))
                .dataModule(new DataModule())
                .build();

        applicationComponent.inject(this);

    }

    private void setupStetho() {
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(
                                RealmInspectorModulesProvider.builder(this)
                                    .withDescendingOrder()
                                    .withLimit(1000)
                                    .databaseNamePattern(Pattern.compile(".+.realm"))
                                    .build())
                        .build());
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }

    public static ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
