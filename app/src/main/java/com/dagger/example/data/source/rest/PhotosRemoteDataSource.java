package com.dagger.example.data.source.rest;

import com.dagger.example.data.entities.Photo;
import com.dagger.example.data.entities.PhotoDto;
import com.dagger.example.data.source.PhotosDataSource;
import com.dagger.example.features.main.utils.PhotoMapper;

import java.util.ArrayList;
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
    public void addPhotos(List<PhotoDto> photoDtoList) {

    }

    @Override
    public void addPhoto(PhotoDto photoDto) {

    }

    @Override
    public List<PhotoDto> getPhotos(LoadPhotosCallback callback) {
        unsplashService.getPhotos(apiKey).enqueue(new Callback<List<Photo>>() {
            @Override
            public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {
                if (response!=null && response.isSuccessful()) {
                    Timber.i("SUCCESS");

                    List<PhotoDto> photoDtoList = new ArrayList<>();

                    // map objects from Photo to PhotoDto using PhotoMapper
                    PhotoMapper photoMapper = new PhotoMapper();
                    for (Photo photo: response.body()){
                        PhotoDto photoDto = photoMapper.from(photo);
                        photoDtoList.add(photoDto);
                    }

                    callback.onPhotosLoaded(photoDtoList);
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
