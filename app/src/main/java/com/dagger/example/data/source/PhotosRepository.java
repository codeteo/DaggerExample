package com.dagger.example.data.source;

import android.app.Application;

import com.dagger.example.data.entities.Photo;
import com.dagger.example.utils.NetworkUtils;

import java.util.List;

import javax.inject.Inject;

/**
 * Concrete implementation of {@link PhotosDataSource} to load photos from the data sources.
 */

public class PhotosRepository implements PhotosDataSource{

    private final PhotosDataSource localDataSource;

    private final PhotosDataSource remoteDataSource;

    private Application application;

    @Inject
    public PhotosRepository(@Local PhotosDataSource localDataSource,
                            @Remote PhotosDataSource remoteDataSource, Application application) {
        this.localDataSource = localDataSource;
        this.remoteDataSource = remoteDataSource;
        this.application = application;
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
        NetworkUtils networkUtils = new NetworkUtils(application);
        if (networkUtils.isOnline()) {
            remoteDataSource.getPhotos(callback);
        } else {
            return localDataSource.getPhotos(callback);
        }

        return null;
    }
}
