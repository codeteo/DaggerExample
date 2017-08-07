package com.dagger.example.features.main.dagger;

import com.dagger.example.data.source.rest.UnsplashService;
import com.dagger.example.utils.FragmentScope;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Dagger Module to provide {@link UnsplashService} for {@link com.dagger.example.features.main.MainPresenter}
 */
@Module
public class MainServiceModule {

    @Provides
    @FragmentScope
    UnsplashService provideUnsplashService(Retrofit retrofit) {
        return retrofit.create(UnsplashService.class);
    }

}

