package com.dagger.example.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Helper class for network (availability etc...)
 */

public class NetworkUtils {

    private Context context;

    public NetworkUtils(Context context){
        this.context = context;
    }

    /**
     * @return true if device is connected to network, else false
     */
    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

}