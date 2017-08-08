package com.dagger.example.features.main;

import android.app.Application;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;

import com.dagger.example.data.entities.PhotoDto;

import java.io.File;

import io.realm.Realm;
import io.realm.RealmResults;
import timber.log.Timber;

/**
 * Downloads images from network and saves them to app's internal storage.
 * Uses {@link DownloadManager}
 */

public class DownloadPhotosManager {

    private static final String SUFFIX = "jpeg";

    private DownloadManager.Request request;
    private BroadcastReceiver broadcastReceiver;

    private Realm realm;
    private Application application;

    private String firstUrl;

    public DownloadPhotosManager(Realm realm, Application application) {
        this.realm = realm;
        this.application = application;
    }

    public void execute() {
        getUrlsFromDB();
        executeRequest();
    }

    private void getUrlsFromDB() {
        final RealmResults<PhotoDto> realmUrls;

        realmUrls = realm.where(PhotoDto.class)
                .findAll();

        Timber.i("After Transaction");

        firstUrl = realmUrls.get(0).getId();
        Timber.i("url == %s", firstUrl);
    }

    private void executeRequest() {
        Timber.i("INSIDE executeRequest");

        try {
            request = new DownloadManager.Request(Uri.parse(firstUrl));
        } catch (IllegalArgumentException e) {
            Timber.i("Error == %s", e.getMessage());
        }

        // allow mobile and WiFi downloads
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
        request.setTitle("DM Example");
        request.setDescription("Downloading file");

        // set the destination path for this download
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS +
                File.separator + "image_test", "randomName" + "." + SUFFIX);
        request.allowScanningByMediaScanner();

        final DownloadManager dm = (DownloadManager) application.getSystemService(Context.DOWNLOAD_SERVICE);
        // this is our unique download id
        final long DL_ID = dm.enqueue(request);

        // get notified when the download is complete
        broadcastReceiver = new BroadcastReceiver() {

            @Override
            public void onReceive(Context context, Intent intent) {

                // our download
                if (DL_ID == intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1L)) {

                    // get the path of the downloaded file
                    DownloadManager.Query query = new DownloadManager.Query();
                    query.setFilterById(DL_ID);
                    Cursor cursor = dm.query(query);
                    if (!cursor.moveToFirst()) {
                        Timber.i("Download error: cursor is empty");
                        return;
                    }

                    if (cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS))
                            != DownloadManager.STATUS_SUCCESSFUL) {
                        Timber.i("Download failed: no success status");
                        return;
                    }

                    String path = cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_LOCAL_URI));
                    Timber.i("File download complete. Location: \n" + path);
                }
            }
        };

        // register receiver to listen for ACTION_DOWNLOAD_COMPLETE action
        // registerReceiver(mDLCompleteReceiver, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));

    }

}
