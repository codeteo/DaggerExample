package com.dagger.example.data.source.rest;

import com.dagger.example.data.entities.Photo;
import com.dagger.example.data.entities.PhotoDto;
import com.dagger.example.data.source.PhotosDataSource;
import com.dagger.example.features.main.utils.PhotoMapper;
import com.dagger.example.utils.schedulers.BaseSchedulerProvider;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Observable;

/**
 *  Implementation of the remote data source
 */

public class PhotosRemoteDataSource implements PhotosDataSource {

    private UnsplashService unsplashService;
    private String apiKey;
    private BaseSchedulerProvider schedulerProvider;

    @Inject
    public PhotosRemoteDataSource(UnsplashService unsplashService,
                            @Named("Api-Key") String apiKey, BaseSchedulerProvider schedulerProvider) {
        this.unsplashService = unsplashService;
        this.apiKey = apiKey;
        this.schedulerProvider = schedulerProvider;
    }

    @Override
    public void addPhotos(List<PhotoDto> photoDtoList) {

    }

    @Override
    public void addPhoto(PhotoDto photoDto) {

    }

    @Override
    public Observable<List<PhotoDto>> getPhotos() {
        return unsplashService.getPhotos(apiKey)
                .map(photoList -> {
                    List<PhotoDto> photoDtoList = new ArrayList<>();

                    // map objects from Photo to PhotoDto using PhotoMapper
                    PhotoMapper photoMapper = new PhotoMapper();
                    for (Photo photo : photoList) {
                        PhotoDto photoDto = photoMapper.from(photo);
                        photoDtoList.add(photoDto);
                    }

                    return photoDtoList;
                })
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.androidMainThread());

    }

}
