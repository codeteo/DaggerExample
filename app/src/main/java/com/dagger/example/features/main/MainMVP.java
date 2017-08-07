package com.dagger.example.features.main;

import com.dagger.example.data.entities.Photo;

import java.util.List;

/**
 * Specifies the contract between the View and the Presenter.
 */

public interface MainMVP {

    interface View {

        void showPhotos(List<Photo> photoList);

    }

    interface Presenter {

        void getPhotos();

    }

}
