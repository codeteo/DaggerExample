package com.dagger.example.data.source;

import com.dagger.example.data.entities.Photo;

import java.util.List;

/**
 * Main entry point for accessing photos.
 */

public interface PhotosDataSource {

    void addPhotos(List<Photo> photoList);

    void addPhoto(Photo photo);

    List<Photo> getPhotos(LoadPhotosCallback callback);

    interface LoadPhotosCallback {

        void onPhotosLoaded(List<Photo> photoList);

        void onDataNotAvailable();

    }

}
