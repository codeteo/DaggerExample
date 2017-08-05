package com.dagger.example.dagger;

import com.dagger.example.MyApplication;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Created by css on 8/4/17.
 */

@Singleton
@Component(modules = {AndroidSupportInjectionModule.class, ApplicationModule.class, NetworkModule.class})
public interface ApplicationComponent {

    void inject(MyApplication application);
}