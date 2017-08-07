package com.dagger.example.data.entities;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by css on 8/7/17.
 */

public class Photo implements Serializable {

    @SerializedName("id") private String id;
    @SerializedName("created_at") private String createdAt;
    @SerializedName("updated_at") private String updatedAt;
    @SerializedName("width") private int width;
    @SerializedName("height") private int height;
    @SerializedName("color") private String color;
    @SerializedName("likes") private int likes;
    @SerializedName("liked_by_user") private boolean likedByUser;
    @SerializedName("description") private String description;
    @SerializedName("user") private User user;
    @SerializedName("profile_image") private ProfileImage profileImage;
    @SerializedName("current_user_collections") private List<CurrentUserCollections> currentUserCollections = null;
    @SerializedName("urls") private Urls urls;
    @SerializedName("categories") private List<String> categories = null;
    @SerializedName("links") private Links_ links;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Urls getUrls() {
        return urls;
    }

    public void setUrls(Urls urls) {
        this.urls = urls;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public Links_ getLinks() {
        return links;
    }

    public void setLinks(Links_ links) {
        this.links = links;
    }

    public boolean isLikedByUser() {
        return likedByUser;
    }

    public void setLikedByUser(boolean likedByUser) {
        this.likedByUser = likedByUser;
    }

    public ProfileImage getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(ProfileImage profileImage) {
        this.profileImage = profileImage;
    }

    public List<CurrentUserCollections> getCurrentUserCollections() {
        return currentUserCollections;
    }

    public void setCurrentUserCollections(List<CurrentUserCollections> currentUserCollections) {
        this.currentUserCollections = currentUserCollections;
    }
}
