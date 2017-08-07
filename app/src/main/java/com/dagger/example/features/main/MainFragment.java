package com.dagger.example.features.main;

import android.os.Bundle;

/**
 * Displays weather data
 */

public class MainFragment extends android.support.v4.app.Fragment {

    public static MainFragment newInstance() {
        
        Bundle args = new Bundle();
        
        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
