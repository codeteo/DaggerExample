package com.dagger.example.dagger;

import android.content.Context;

import com.dagger.example.Constants;
import com.dagger.example.MyApplication;
import com.dagger.example.utils.BaseUrlInterceptor;
import com.dagger.example.utils.ForTestingPurposes;

import javax.inject.Singleton;

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

    @Provides
    @Singleton
    @ForTestingPurposes
    static BaseUrlInterceptor providesBaseUrlInterceptor() {
        return new BaseUrlInterceptor(Constants.BASE_URL);
    }

}
