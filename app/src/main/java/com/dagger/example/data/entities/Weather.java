package com.dagger.example.data.entities;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

/**
 * Inner class for {@link CityWeather}
 */
@AutoValue
public abstract class Weather implements Parcelable {

    public abstract int getId();
    public abstract String getMain();
    public abstract String getDescription();
    public abstract String getIcon();

    public static Builder builder() {
        return new $$AutoValue_Weather.Builder();
    }

    @AutoValue.Builder
    public static abstract class Builder {
        public abstract Builder setId(int id);
        public abstract Builder setMain(String main);
        public abstract Builder setDescription(String description);
        public abstract Builder setIcon(String icon);

        public abstract Weather build();
    }

    public static TypeAdapter<Weather> typeAdapter(Gson gson) {
        return new $AutoValue_Weather.GsonTypeAdapter(gson);
    }

}
