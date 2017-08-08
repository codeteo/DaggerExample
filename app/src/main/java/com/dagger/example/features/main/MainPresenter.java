package com.dagger.example.features.main;

import com.dagger.example.data.entities.Photo;
import com.dagger.example.data.source.PhotosDataSource.LoadPhotosCallback;
import com.dagger.example.data.source.PhotosRepository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import timber.log.Timber;

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
        repository.getPhotos(new LoadPhotosCallback() {
            @Override
            public void onPhotosLoaded(List<Photo> photoList) {
                Timber.i("onPhotos Loaded");
                view.showPhotos(photoList);
            }

            @Override
            public void onDataNotAvailable() {
                // show error/empty
            }
        });
    }
}
