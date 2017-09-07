package com.dagger.example.data.source.local;

import android.app.Application;

import com.dagger.example.data.entities.PhotoDto;
import com.dagger.example.data.source.PhotosDataSource;
import com.dagger.example.features.main.DownloadPhotosManager;
import com.dagger.example.utils.schedulers.BaseSchedulerProvider;

import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;
import rx.Observable;
import timber.log.Timber;

/**
 * Concrete implementation of a data source as a db.
 */

public class PhotosLocalDataSource implements PhotosDataSource {

    private Realm realm;
    private Application application;
    private BaseSchedulerProvider schedulerProvider;

    @Inject
    public PhotosLocalDataSource(Realm realm, Application application, BaseSchedulerProvider schedulerProvider) {
        this.realm = realm;
        this.application = application;
        this.schedulerProvider = schedulerProvider;
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
    public Observable<List<PhotoDto>> getPhotos() {
        return Observable.create(subscriber -> {
            List<PhotoDto> photoDtoList;

            try(Realm realmInstance = realm.getDefaultInstance()) {
                photoDtoList = realmInstance.where(PhotoDto.class)
                        .findAll();

                subscriber.onNext(photoDtoList);
            }
        });
    }
}
