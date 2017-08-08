package com.dagger.example.data.source;

import com.dagger.example.data.entities.Photo;

import java.util.List;

import javax.inject.Inject;

/**
 * Concrete implementation of {@link PhotosDataSource} to load photos from the data sources.
 */

public class PhotosRepository implements PhotosDataSource{

    private final PhotosDataSource localDataSource;

    private final PhotosDataSource remoteDataSource;

    @Inject
    public PhotosRepository(@Local PhotosDataSource localDataSource,
                        @Remote PhotosDataSource remoteDataSource) {
        this.localDataSource = localDataSource;
        this.remoteDataSource = remoteDataSource;
    }

    @Override
    public void addPhotos(List<Photo> photoList) {

    }

    @Override
    public void addPhoto(Photo photo) {

    }

    /**
     * Starts a call to get the data from the network and at the same time
     * responds with the data from database.
     */
    @Override
    public List<Photo> getPhotos(LoadPhotosCallback callback) {
        remoteDataSource.getPhotos(callback);

        return localDataSource.getPhotos(callback);
    }
}
