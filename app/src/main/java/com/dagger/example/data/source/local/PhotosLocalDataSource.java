package com.dagger.example.data.source.local;

import com.dagger.example.data.entities.Photo;
import com.dagger.example.data.entities.PhotoDto;
import com.dagger.example.data.source.PhotosDataSource;
import com.dagger.example.features.main.utils.PhotoMapper;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;

/**
 * Concrete implementation of a data source as a db.
 */

public class PhotosLocalDataSource implements PhotosDataSource {

    private Realm realm;

    private PhotoMapper photoMapper;

    @Inject
    public PhotosLocalDataSource(Realm realm) {
        this.realm = realm;
    }

    @Override
    public void addPhotos(List<Photo> photoList) {
        List<PhotoDto> photoDtoList = new ArrayList<>();
        // run mapper
        photoMapper = new PhotoMapper();
        for (Photo photo: photoList){
            PhotoDto photoDto = photoMapper.from(photo);
            photoDtoList.add(photoDto);
        }

        // save photos
        try(Realm realmInstance = realm) {
            realmInstance.executeTransaction(realm1 -> realm1.insertOrUpdate(photoDtoList));
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
