package com.dagger.example.features.main;

import com.dagger.example.data.rest.UnsplashService;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

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
        unsplashService.getPhotos().enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Timber.i("onResponse");
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Timber.i("onFailure");
            }
        });
    }
}
