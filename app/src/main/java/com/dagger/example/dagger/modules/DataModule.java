package com.dagger.example.dagger.modules;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import timber.log.Timber;

/**
 * Dagger Module to provide permanent storage dependencies.
 */
@Module
public class DataModule {

    private static final int DATABASE_VERSION = 1;

    @Provides
    @Singleton
    RealmConfiguration provideRealmConfig(Application application) {
        Realm.init(application);

        return new RealmConfiguration.Builder()
                .schemaVersion(DATABASE_VERSION)
                .build();
    }

    @Provides
    @Singleton
    Realm provideRealm(RealmConfiguration config) {
        Realm.setDefaultConfiguration(config);
        try {
            return Realm.getDefaultInstance();
        } catch (Exception e) {
            Timber.e(e, "");
            Realm.deleteRealm(config);
            Realm.setDefaultConfiguration(config);
            return Realm.getDefaultInstance();
        }
    }

}