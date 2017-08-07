package com.dagger.example.features.main;

import com.dagger.example.data.source.PhotosRepository;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Presenter class for {@link MainFragment}
 */

public class MainPresenter implements MainMVP.Presenter {

    private MainMVP.View view;
//    private UnsplashService unsplashService;
    private PhotosRepository repository;
    private String apiKey;

    @Inject
    public MainPresenter(MainMVP.View view, PhotosRepository repository,  @Named("Api-Key") String apiKey) {
        this.view = view;
//        this.unsplashService = unsplashService;
        this.repository = repository;
        this.apiKey = apiKey;
    }

    @Override
    public void getPhotos() {
        repository.getPhotos();
    }
}
