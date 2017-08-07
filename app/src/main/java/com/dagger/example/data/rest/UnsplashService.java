package com.dagger.example.data.rest;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Unsplash API
 */

public interface UnsplashService {

    @GET("photos")
    Call<Void> getPhotos(@Query("client_id") String apiKey);

}
