package com.dagger.example.dagger;

import android.content.Context;

import com.dagger.example.MyApplication;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger Module which builds Singleton dependencies.
 */
@Module
public class ApplicationModule {

    @Provides
    Context provideContext(MyApplication application) {
        return application.getApplicationContext();
    }

}
