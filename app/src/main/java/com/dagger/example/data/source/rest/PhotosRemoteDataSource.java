package com.dagger.example.data.source.rest;

import com.dagger.example.data.entities.Photo;
import com.dagger.example.data.source.PhotosDataSource;

import java.util.List;

/**
 *  Implementation of the remote data source
 */

public class PhotosRemoteDataSource implements PhotosDataSource {

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
