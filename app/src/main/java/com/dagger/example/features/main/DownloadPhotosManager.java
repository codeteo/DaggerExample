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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import timber.log.Timber;

/**
 * Downloads images from network and saves them to app's internal storage.
 * Uses {@link DownloadManager}
 */

public class DownloadPhotosManager {

    private static final String SUFFIX = "jpeg";
    private static final String DIR_NAME = "Dagger";

    private BroadcastReceiver broadcastReceiver;

    private Realm realm;
    private Application application;

    private RealmResults<PhotoDto> realmUrls;
    private List<PhotoDto> toBeDownloadedList = new ArrayList<>();

    public DownloadPhotosManager(Realm realm, Application application) {
        this.realm = realm;
        this.application = application;
    }

    public void execute() {
        getUrlsFromDB();

        executeRequest();
    }

    private void getUrlsFromDB() {
        try(Realm realmInstance = realm.getDefaultInstance()) {

            realmUrls = realmInstance.where(PhotoDto.class)
                    .findAll();

            // remove urls that already have a photo stored
            removeDownloadedPhotosUrls(getPhotosFilenames());
        }
    }

    private void executeRequest() {
        Timber.i("START executeRequest");

        final DownloadManager downloadManager = (DownloadManager) application.getSystemService(Context.DOWNLOAD_SERVICE);

        // contains ID for each download request
        final long[] DL_ID = new long[toBeDownloadedList.size()];

        for (int i = 0; i < toBeDownloadedList.size(); i++) {
            DL_ID[i] = downloadManager.enqueue(createRequestForDM(toBeDownloadedList.get(i)));
        }

        // get notified when the downloadManager is complete
        broadcastReceiver = new BroadcastReceiver() {

            @Override
            public void onReceive(Context context, Intent intent) {

                // ID of completed download
                long completedDownloadID = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1L);

                Timber.i("MESA sto onReceive with completedID == %d", completedDownloadID);

                if (Arrays.asList(DL_ID).contains(completedDownloadID)) {

                    // get the path of the downloaded file
                    DownloadManager.Query query = new DownloadManager.Query();
                    query.setFilterById(completedDownloadID);
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
        // TODO: 8/9/17 Dont forget to unregister receiver
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
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_PICTURES +
                File.separator + DIR_NAME, photoDto.getId() + "." + SUFFIX);
        request.allowScanningByMediaScanner();

        return request;
    }

    /**
     * Get the filenames of the files stored in "Pictures/Dagger" directory.
     * @return list of filenames without the ".jpeg" suffix.
     */
    private List<String> getPhotosFilenames() {
        List<String> filenamesList = new ArrayList<>();

        File picturesDir = new File(Environment.getExternalStorageDirectory(), "Pictures");
        File daggerDir = new File(picturesDir, DIR_NAME);

        if (daggerDir.exists()) {
            for (File file : daggerDir.listFiles()) {
                if (file.isFile()) {
                    String fileName = file.getName();
                    filenamesList.add(stripSuffix(fileName));
                }
            }
        }

        return filenamesList;
    }

    /**
     * Removes ".jpeg" suffix from filename
     * @return filename without suffix
     */
    private String stripSuffix(String filename) {
        return filename.replace(".jpeg", "");
    }

    /**
     * Removes urls from the list of to-be-downloaded urls that already have
     *
     */
    private void removeDownloadedPhotosUrls(List<String> filenames) {
        for (PhotoDto photoDto:realmUrls){
            if (!filenames.contains(photoDto.getId())) {
                toBeDownloadedList.add(photoDto);
            }
        }
    }
}
