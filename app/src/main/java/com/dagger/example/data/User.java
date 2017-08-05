package com.dagger.example.data;

import android.os.Parcelable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

/**
 * Entity class models the response from network request
 */
@AutoValue
public abstract class User implements Parcelable {
    @SerializedName("login") public abstract String getLogin();
    @SerializedName("avatar_url") public abstract String getAvatarUrl();

    public static Builder builder() {
        return new AutoValue_User.Builder();
    }

    @AutoValue.Builder
    public static abstract class Builder {
        public abstract Builder setLogin(String login);
        public abstract Builder setAvatarUrl(String avatarUrl);

        public abstract User build();
    }

    public static TypeAdapter<User> typeAdapter(Gson gson) {
        return new AutoValue_User.GsonTypeAdapter(gson);
    }
}
