package com.dagger.example.data.entities;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by css on 8/7/17.
 */

public class Links_ implements Serializable {

    @SerializedName("self") private String self;
    @SerializedName("html") private String html;
    @SerializedName("download") private String download;
    @SerializedName("download_location") private String downloadLocation;
    @SerializedName("photos") private String photos;

    public String getSelf() {
        return self;
    }

    public void setSelf(String self) {
        this.self = self;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public String getDownload() {
        return download;
    }

    public void setDownload(String download) {
        this.download = download;
    }

    public String getDownloadLocation() {
        return downloadLocation;
    }

    public void setDownloadLocation(String downloadLocation) {
        this.downloadLocation = downloadLocation;
    }
}
