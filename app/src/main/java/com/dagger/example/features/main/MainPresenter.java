package com.dagger.example.features.main;

import com.dagger.example.data.rest.UnsplashService;

import javax.inject.Inject;

/**
 * Presenter class for {@link MainFragment}
 */

public class MainPresenter implements MainMVP.Presenter {

    private MainMVP.View view;
    private UnsplashService unsplashService;

    @Inject
    public MainPresenter(MainMVP.View view, UnsplashService unsplashService) {
        this.view = view;
        this.unsplashService = unsplashService;
    }

    @Override
    public void getPhotos() {

    }
}
