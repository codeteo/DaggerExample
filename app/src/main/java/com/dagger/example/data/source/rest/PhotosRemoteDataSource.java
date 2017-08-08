package com.dagger.example.data.source.rest;

import com.dagger.example.data.entities.Photo;
import com.dagger.example.data.source.PhotosDataSource;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

/**
 *  Implementation of the remote data source
 */

public class PhotosRemoteDataSource implements PhotosDataSource {

    private UnsplashService unsplashService;
    private String apiKey;

    @Inject
    public PhotosRemoteDataSource(UnsplashService unsplashService, @Named("Api-Key") String apiKey) {
        this.unsplashService = unsplashService;
        this.apiKey = apiKey;
    }

    @Override
    public void addPhotos(List<Photo> photoList) {

    }

    @Override
    public void addPhoto(Photo photo) {

    }

    @Override
    public List<Photo> getPhotos(LoadPhotosCallback callback) {
        unsplashService.getPhotos(apiKey).enqueue(new Callback<List<Photo>>() {
            @Override
            public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {
                if (response!=null && response.isSuccessful()) {
                    Timber.i("SUCCESS");
                    callback.onPhotosLoaded(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Photo>> call, Throwable t) {
                Timber.i("onFailure");
            }
        });

        return null;
    }

}
