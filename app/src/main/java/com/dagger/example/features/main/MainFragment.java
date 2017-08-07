package com.dagger.example.features.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dagger.example.MyApplication;
import com.dagger.example.R;
import com.dagger.example.features.main.dagger.DaggerMainComponent;

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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerMainComponent.builder()
                .applicationComponent(MyApplication.getApplicationComponent())
                .build()
                .inject(this);
    }

}
