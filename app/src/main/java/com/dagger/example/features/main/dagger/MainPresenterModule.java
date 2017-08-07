package com.dagger.example.features.main.dagger;

import com.dagger.example.data.rest.UnsplashService;
import com.dagger.example.features.main.MainMVP;
import com.dagger.example.utils.FragmentScope;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger Module provides dependencies to {@link com.dagger.example.features.main.MainPresenter}
 */

@Module
public class MainPresenterModule {

    private final MainMVP.View view;
    private final UnsplashService unsplashService;

    public MainPresenterModule(MainMVP.View view, UnsplashService unsplashService) {
        this.view = view;
        this.unsplashService = unsplashService;
    }

    @Provides
    @FragmentScope
    MainMVP.View provideMainView() {
        return view;
    }

    @Provides
    @FragmentScope
    UnsplashService provideUnsplashService() {
        return unsplashService;
    }
}
