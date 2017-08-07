package com.dagger.example.dagger.components;

import android.app.Application;
import android.content.SharedPreferences;

import com.dagger.example.MyApplication;
import com.dagger.example.dagger.modules.ApplicationModule;
import com.dagger.example.dagger.modules.DataModule;
import com.dagger.example.dagger.modules.NetworkModule;
import com.dagger.example.utils.BaseUrlInterceptor;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;
import io.realm.Realm;
import okhttp3.HttpUrl;
import retrofit2.Retrofit;

/**
 * Dagger Component builds the object graph for singleton scoped dependencies.
 */

@Singleton
@Component(modules = {AndroidSupportInjectionModule.class, ApplicationModule.class, NetworkModule.class, DataModule.class})
public interface ApplicationComponent {

    void inject(MyApplication application);

    /** exposed dependencies to downstream components (e.g. NetworkComponent) **/

    Application getApplication();

    SharedPreferences getSharedPreferences();

    HttpUrl baseUrl();

    Retrofit retrofit();

    // exposes Interceptor to use it for testing with mockWebServer
    BaseUrlInterceptor baseUrlInterceptor();

    Realm realm();

}