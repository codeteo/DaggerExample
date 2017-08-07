package com.dagger.example.data.entities;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by css on 8/7/17.
 */

public class ProfileImage implements Serializable{

    @SerializedName("small") private String small;
    @SerializedName("medium") private String medium;
    @SerializedName("large") private String large;

    public String getSmall() {
        return small;
    }

    public void setSmall(String small) {
        this.small = small;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getLarge() {
        return large;
    }

    public void setLarge(String large) {
        this.large = large;
    }
}
