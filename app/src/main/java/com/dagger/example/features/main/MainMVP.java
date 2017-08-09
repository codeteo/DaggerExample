package com.dagger.example.features.main;

import com.dagger.example.data.entities.PhotoDto;

import java.util.List;

/**
 * Specifies the contract between the View and the Presenter.
 */

public interface MainMVP {

    interface View {

        void showPhotos(List<PhotoDto> photoList);

    }

    interface Presenter {

        void getPhotos();

    }

}
