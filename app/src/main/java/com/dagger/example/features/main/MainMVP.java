package com.dagger.example.features.main;

/**
 * Specifies the contract between the View and the Presenter.
 */

public interface MainMVP {

    interface View {

        void showPhotos();

    }

    interface Presenter {

        void getPhotos();

    }

}
