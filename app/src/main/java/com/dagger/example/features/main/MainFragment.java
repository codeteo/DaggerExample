package com.dagger.example.features.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dagger.example.MyApplication;
import com.dagger.example.R;
import com.dagger.example.features.main.dagger.DaggerMainComponent;
import com.dagger.example.features.main.dagger.MainPresenterModule;

import javax.inject.Inject;

/**
 * Displays weather data
 */

public class MainFragment extends android.support.v4.app.Fragment implements MainMVP.View {

    @Inject
    MainPresenter presenter;

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
                .mainPresenterModule(new MainPresenterModule(this))
                .build()
                .inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        presenter.getPhotos();

        return view;
    }

    @Override
    public void showPhotos() {

    }
}
