package com.dagger.example.data.entities;

import io.realm.RealmObject;
import io.realm.annotations.Index;

/**
 * Realm object for {@link Photo}
 */

public class PhotoDto extends RealmObject {

    @Index
    private String id;

    private String url;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
