package com.dagger.example.data;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

/**
 * Inner class for {@link CityWeather}
 */

@AutoValue
public abstract class Main implements Parcelable {

    public abstract double temp();
    public abstract double pressure();
    public abstract double humidity();

    @SerializedName("temp_min")
    public abstract double tempMin();

    @SerializedName("temp_max")
    public abstract double tempMax();

    public static Builder builder() {
        return new AutoValue_Main.Builder();
    }

    @AutoValue.Builder
    public static abstract class Builder {
        public abstract Builder setTemp(double temp);
        public abstract Builder setPressure(double pressure);
        public abstract Builder setHumidity(double humidity);
        public abstract Builder setTempMin(double tempMin);
        public abstract Builder setTempMax(double tempMax);

        public abstract Main build();
    }

    public static TypeAdapter<Main> typeAdapter(Gson gson) {
        return new AutoValue_Main.GsonTypeAdapter(gson);
    }

}
