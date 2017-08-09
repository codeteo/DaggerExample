package com.dagger.example.data.source;

import com.dagger.example.data.entities.PhotoDto;

import java.util.List;

/**
 * Main entry point for accessing photos.
 */

public interface PhotosDataSource {

    void addPhotos(List<PhotoDto> photoList);

    void addPhoto(PhotoDto photo);

    List<PhotoDto> getPhotos(LoadPhotosCallback callback);

    interface LoadPhotosCallback {

        void onPhotosLoaded(List<PhotoDto> photoList);

        void onDataNotAvailable();

    }

}
