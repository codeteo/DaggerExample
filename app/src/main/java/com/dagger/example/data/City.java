package com.dagger.example.data;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

/**
 * Inner class for {@link CityWeather}
 */

@AutoValue
public abstract class City implements Parcelable{

    public abstract long getId();
    public abstract String getName();

    public static Builder builder() {
        return new AutoValue_City.Builder();
    }

    @AutoValue.Builder
    public static abstract class Builder {
        public abstract Builder setId(long id);
        public abstract Builder setName(String name);

        public abstract City build();
    }

    public static TypeAdapter<City> typeAdapter(Gson gson) {
        return new AutoValue_City.GsonTypeAdapter(gson);
    }

}
