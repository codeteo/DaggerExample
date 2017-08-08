package com.dagger.example.features.main.utils;

import com.dagger.example.data.entities.Photo;
import com.dagger.example.data.entities.PhotoDto;

/**
 * Maps objects from {@link Photo} to {@link PhotoDto} so we can store
 * them to our Realm DB.
 */

public class PhotoMapper {

    public PhotoDto from(Photo photo) {
        PhotoDto photoDto = new PhotoDto();

        photoDto.setId(photo.getId());
        photoDto.setUrl(photo.getUrls().getFull());

        return photoDto;
    }
}
