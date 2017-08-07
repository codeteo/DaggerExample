package com.dagger.example.data.rest;

import retrofit2.http.GET;

/**
 * Unsplash API
 */

public interface UnsplashService {

    @GET("photos")
    void getPhotos();

}
