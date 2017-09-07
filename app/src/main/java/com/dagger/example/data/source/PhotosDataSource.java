package com.dagger.example.data.source;

import com.dagger.example.data.entities.PhotoDto;

import java.util.List;

import rx.Observable;

/**
 * Main entry point for accessing photos.
 */

public interface PhotosDataSource {

    void addPhotos(List<PhotoDto> photoList);

    void addPhoto(PhotoDto photo);

    Observable<List<PhotoDto>> getPhotos();

}
