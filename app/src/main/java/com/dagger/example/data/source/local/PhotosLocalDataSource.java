package com.dagger.example.data.source.local;

import android.app.Application;

import com.dagger.example.data.entities.PhotoDto;
import com.dagger.example.data.source.PhotosDataSource;
import com.dagger.example.features.main.DownloadPhotosManager;

import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;
import timber.log.Timber;

/**
 * Concrete implementation of a data source as a db.
 */

public class PhotosLocalDataSource implements PhotosDataSource {

    private Realm realm;
    private Application application;

    @Inject
    public PhotosLocalDataSource(Realm realm, Application application) {
        this.realm = realm;
        this.application = application;
    }

    @Override
    public void addPhotos(List<PhotoDto> photoDtoList) {

        // save photos
        try(Realm realmInstance = realm.getDefaultInstance()) {
            realmInstance.executeTransaction(realm1 -> realm1.insertOrUpdate(photoDtoList));

            Timber.i("Before Execute");
            DownloadPhotosManager downloadPhotosManager = new DownloadPhotosManager(realm, application);
            downloadPhotosManager.execute();
            Timber.i("After Execute");

        }

    }

    @Override
    public void addPhoto(PhotoDto photoDto) {

    }

    @Override
    public List<PhotoDto> getPhotos(LoadPhotosCallback callback) {
        List<PhotoDto> photoDtoList;

        try(Realm realmInstance = realm.getDefaultInstance()) {
            photoDtoList = realmInstance.where(PhotoDto.class)
                    .findAll();
        }

        Timber.i("SIZE ======= %d", photoDtoList.size());

        callback.onPhotosLoaded(photoDtoList);

        return null;
    }
}
