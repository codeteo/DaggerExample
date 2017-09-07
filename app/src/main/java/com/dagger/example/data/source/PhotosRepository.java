package com.dagger.example.data.source;

import android.app.Application;

import com.dagger.example.data.entities.PhotoDto;
import com.dagger.example.utils.NetworkUtils;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;

/**
 * Concrete implementation of {@link PhotosDataSource} to load photos from the data sources.
 */

public class PhotosRepository implements PhotosDataSource {

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
    public void addPhotos(List<PhotoDto> photoDtoList) {

    }

    @Override
    public void addPhoto(PhotoDto photo) {

    }

    /**
     * Starts a call to get the data from the network and at the same time
     * responds with the data from database.
     */
    @Override
    public Observable<List<PhotoDto>> getPhotos() {
        NetworkUtils networkUtils = new NetworkUtils(application);
        if (networkUtils.isOnline()) {
            return remoteDataSource.getPhotos();
        } else {
            localDataSource.getPhotos();
        }

        return null;
    }
}
