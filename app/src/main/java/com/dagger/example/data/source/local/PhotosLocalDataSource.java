package com.dagger.example.data.source.local;

import com.dagger.example.data.entities.Photo;
import com.dagger.example.data.source.PhotosDataSource;

import java.util.List;

/**
 * Concrete implementation of a data source as a db.
 */

public class PhotosLocalDataSource implements PhotosDataSource {


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
