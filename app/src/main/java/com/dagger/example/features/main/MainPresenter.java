package com.dagger.example.features.main;

import com.dagger.example.data.entities.Photo;
import com.dagger.example.data.source.rest.UnsplashService;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

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
    private String apiKey;

    @Inject
    public MainPresenter(MainMVP.View view, UnsplashService unsplashService, @Named("Api-Key") String apiKey) {
        this.view = view;
        this.unsplashService = unsplashService;
        this.apiKey = apiKey;
    }

    @Override
    public void getPhotos() {
        unsplashService.getPhotos(apiKey).enqueue(new Callback<List<Photo>>() {
            @Override
            public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {
                if (response!=null && response.isSuccessful()) {
                    view.showPhotos(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Photo>> call, Throwable t) {
                Timber.i("onFailure");
            }
        });
    }
}
