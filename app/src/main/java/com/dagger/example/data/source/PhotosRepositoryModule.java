package com.dagger.example.data.source;

import android.app.Application;

import com.dagger.example.data.source.local.PhotosLocalDataSource;
import com.dagger.example.data.source.rest.PhotosRemoteDataSource;
import com.dagger.example.data.source.rest.UnsplashService;
import com.dagger.example.utils.FragmentScope;

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
    PhotosDataSource providePhotosLocalDataSource(Realm realm, Application application) {
        return new PhotosLocalDataSource(realm, application);
    }

    @Provides
    @FragmentScope
    @Remote
    PhotosDataSource providePhotosRemoteDataSource(UnsplashService unsplashService, @Named("Api-Key") String apiKey) {
        return new PhotosRemoteDataSource(unsplashService, apiKey);
    }

}
