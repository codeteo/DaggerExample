package com.dagger.example.data;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Entity class models the response from network request
 */
@AutoValue
public abstract class CityWeather implements Parcelable {
    @SerializedName("id") public abstract long getId();
    @SerializedName("name") public abstract String getName();
    @SerializedName("weather") public abstract List<Weather> getWeather();
    @SerializedName("main") public abstract Main getMain();
    @SerializedName("isFavorite") public abstract boolean getIsFavorite();

    public static Builder builder() {
        return new AutoValue_CityWeather.Builder();
    }

    @AutoValue.Builder
    public static abstract class Builder {
        public abstract Builder setId(long id);
        public abstract Builder setName(String name);
        public abstract Builder setWeather(List<Weather> weather);
        public abstract Builder setMain(Main main);
        public abstract Builder setIsFavorite(boolean isFavorite);

        public abstract CityWeather build();
    }

    public static TypeAdapter<CityWeather> typeAdapter(Gson gson) {
        return new AutoValue_CityWeather.GsonTypeAdapter(gson);
    }
}
