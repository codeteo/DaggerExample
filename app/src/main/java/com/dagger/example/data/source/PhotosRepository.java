package com.dagger.example.data.source;

import android.app.Application;

import com.dagger.example.data.entities.PhotoDto;
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
    public List<PhotoDto> getPhotos(LoadPhotosCallback callback) {
        NetworkUtils networkUtils = new NetworkUtils(application);
        if (networkUtils.isOnline()) {
            remoteDataSource.getPhotos(new LoadPhotosCallback() {
                @Override
                public void onPhotosLoaded(List<PhotoDto> photoList) {
                    // on success : pass photos to presenter to be displayed and
                    // call localDataSource to save them
                    callback.onPhotosLoaded(photoList);
                    localDataSource.addPhotos(photoList);
                }

                @Override
                public void onDataNotAvailable() {
                    callback.onDataNotAvailable();
                }
            });
        } else {
            return localDataSource.getPhotos(new LoadPhotosCallback() {
                @Override
                public void onPhotosLoaded(List<PhotoDto> photoList) {
                    callback.onPhotosLoaded(photoList);
                }

                @Override
                public void onDataNotAvailable() {
                    callback.onDataNotAvailable();
                }
            });
        }

        return null;
    }
}
