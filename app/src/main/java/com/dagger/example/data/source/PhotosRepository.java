package com.dagger.example.data.source;

import com.dagger.example.data.entities.Photo;
import com.dagger.example.data.source.local.PhotosLocalDataSource;
import com.dagger.example.data.source.rest.PhotosRemoteDataSource;

import java.util.List;

/**
 * Concrete implementation of {@link PhotosDataSource} to load photos from the data sources.
 */

public class PhotosRepository implements PhotosDataSource{

    private final PhotosLocalDataSource localDataSource;

    private final PhotosRemoteDataSource remoteDataSource;

    public PhotosRepository(PhotosLocalDataSource localDataSource, PhotosRemoteDataSource remoteDataSource) {
        this.localDataSource = localDataSource;
        this.remoteDataSource = remoteDataSource;
    }

    @Override
    public void addPhotos(List<Photo> photoList) {

    }

    @Override
    public void addPhoto(Photo photo) {

    }

    @Override
    public List<Photo> getPhotos() {
        return null;
    }
}
