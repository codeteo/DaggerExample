package com.dagger.example.features.main;

import android.app.Application;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;

import com.dagger.example.data.entities.PhotoDto;

import java.io.File;
import java.util.Arrays;

import io.realm.Realm;
import io.realm.RealmResults;
import timber.log.Timber;

/**
 * Downloads images from network and saves them to app's internal storage.
 * Uses {@link DownloadManager}
 */

public class DownloadPhotosManager {

    private static final String SUFFIX = "jpeg";

    private BroadcastReceiver broadcastReceiver;

    private Realm realm;
    private Application application;

    private RealmResults<PhotoDto> realmUrls;

    public DownloadPhotosManager(Realm realm, Application application) {
        this.realm = realm;
        this.application = application;
    }

    public void execute() {
        getUrlsFromDB();
        executeRequest();
    }

    private void getUrlsFromDB() {
        realmUrls = realm.where(PhotoDto.class)
                .findAll();
    }

    private void executeRequest() {
        Timber.i("INSIDE executeRequest");

        final DownloadManager downloadManager = (DownloadManager) application.getSystemService(Context.DOWNLOAD_SERVICE);

        // contains ID for each download request
        final long[] DL_ID = new long[realmUrls.size()];

        for (int i = 0; i < realmUrls.size(); i++) {
            DL_ID[i] = downloadManager.enqueue(createRequestForDM(realmUrls.get(i)));
        }

        // get notified when the downloadManager is complete
        broadcastReceiver = new BroadcastReceiver() {

            @Override
            public void onReceive(Context context, Intent intent) {

                // our downloadManager
                long intentID = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1L);

                if (Arrays.asList(DL_ID).contains(intentID)) {

                    // get the path of the downloaded file
                    DownloadManager.Query query = new DownloadManager.Query();
                    query.setFilterById(intentID);
                    Cursor cursor = downloadManager.query(query);
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
                    Timber.i("File downloadManager complete. Location: \n" + path);
                }
            }
        };

        // register receiver to listen for ACTION_DOWNLOAD_COMPLETE action
         application.registerReceiver(broadcastReceiver, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));

    }

    private DownloadManager.Request createRequestForDM(PhotoDto photoDto) {
        DownloadManager.Request request = null;

        try {
            request = new DownloadManager.Request(Uri.parse(photoDto.getDownloadUrl()));
        } catch (IllegalArgumentException e) {
            Timber.i("Error == %s", e.getMessage());
        }

        // allow mobile and WiFi downloads
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
        request.setTitle("DM Example");
        request.setDescription("Downloading file");

        // set the destination path for this download
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS +
                File.separator + "image_test", photoDto.getId() + "." + SUFFIX);
        request.allowScanningByMediaScanner();

        return request;
    }

}
