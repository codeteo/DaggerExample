package com.dagger.example.data.entities;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by css on 8/7/17.
 */

public class CurrentUserCollections implements Serializable {

    private int id;
    private String title;

    @SerializedName("published_at") private String publishedAt;
    @SerializedName("updated_at") private String updatedAt;

    private boolean curated;

    @SerializedName("cover_photo") private Photo coverPhoto;


}
