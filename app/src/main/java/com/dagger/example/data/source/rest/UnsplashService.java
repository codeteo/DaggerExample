package com.dagger.example.data.source.rest;

import com.dagger.example.data.entities.Photo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Unsplash API
 */

public interface UnsplashService {

    @GET("photos")
    Call<List<Photo>> getPhotos(@Query("client_id") String apiKey);
}
