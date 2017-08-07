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

    @Override
    public List<Photo> getPhotos() {
        return null;
    }
}
