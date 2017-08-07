package com.dagger.example.features.main.dagger;

import com.dagger.example.features.main.MainMVP;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger Module provides dependencies to {@link com.dagger.example.features.main.MainPresenter}
 */

@Module
public class MainPresenterModule {

    private final MainMVP.View view;

    public MainPresenterModule(MainMVP.View view) {
        this.view = view;
    }

    @Provides
    MainMVP.View provideMainView() {
        return view;
    }
}
