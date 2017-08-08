package com.dagger.example.data.source.local;

import android.app.Application;

import com.dagger.example.data.entities.Photo;
import com.dagger.example.data.entities.PhotoDto;
import com.dagger.example.data.source.PhotosDataSource;
import com.dagger.example.features.main.DownloadPhotosManager;
import com.dagger.example.features.main.utils.PhotoMapper;

import java.util.ArrayList;
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
    public void addPhotos(List<Photo> photoList) {
        List<PhotoDto> photoDtoList = new ArrayList<>();
        // run mapper
        PhotoMapper photoMapper = new PhotoMapper();
        for (Photo photo: photoList){
            PhotoDto photoDto = photoMapper.from(photo);
            photoDtoList.add(photoDto);
        }

        // save photos
        try(Realm realmInstance = realm) {
            realmInstance.executeTransaction(realm1 -> realm1.insertOrUpdate(photoDtoList));

            Timber.i("Before Execute");
            DownloadPhotosManager downloadPhotosManager = new DownloadPhotosManager(realm, application);
            downloadPhotosManager.execute();
            Timber.i("After Execute");

        }

    }

    @Override
    public void addPhoto(Photo photo) {

    }

    @Override
    public List<Photo> getPhotos(LoadPhotosCallback callback) {
        return null;
    }
}
