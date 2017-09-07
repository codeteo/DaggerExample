package com.dagger.example.data.source;

import android.app.Application;

import com.dagger.example.data.source.local.PhotosLocalDataSource;
import com.dagger.example.data.source.rest.PhotosRemoteDataSource;
import com.dagger.example.data.source.rest.UnsplashService;
import com.dagger.example.utils.FragmentScope;
import com.dagger.example.utils.schedulers.BaseSchedulerProvider;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;


/**
 * This is used by Dagger to inject the required arguments into the {@link PhotosRepository}.
 */
@Module
public class PhotosRepositoryModule {

    @Provides
    @FragmentScope
    @Local
    PhotosDataSource providePhotosLocalDataSource(Realm realm, Application application, BaseSchedulerProvider schedulerProvider) {
        return new PhotosLocalDataSource(realm, application, schedulerProvider);
    }

    @Provides
    @FragmentScope
    @Remote
    PhotosDataSource providePhotosRemoteDataSource(UnsplashService unsplashService,
                                                   @Named("Api-Key") String apiKey, BaseSchedulerProvider schedulerProvider) {
        return new PhotosRemoteDataSource(unsplashService, apiKey, schedulerProvider);
    }

}
