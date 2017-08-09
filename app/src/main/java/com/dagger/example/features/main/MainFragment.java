package com.dagger.example.features.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.dagger.example.MyApplication;
import com.dagger.example.R;
import com.dagger.example.data.entities.Photo;
import com.dagger.example.features.main.adapter.PhotoAdapter;
import com.dagger.example.features.main.dagger.DaggerMainComponent;
import com.dagger.example.features.main.dagger.MainPresenterModule;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

/**
 * Displays weather data
 */

public class MainFragment extends android.support.v4.app.Fragment implements MainMVP.View {

    @BindView(R.id.rv_main_list) RecyclerView rvPhotoList;
    @BindView(R.id.btn_storage_perm) Button btnPermissions;

    @Inject
    MainPresenter presenter;

    private List<Photo> dataset;
    private PhotoAdapter adapter;

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

        presenter.getPhotos();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);

        btnPermissions.setOnClickListener(buttonView -> ((MainActivity) getActivity()).giveStoragePermission());

        adapter = new PhotoAdapter(getActivity());
        setupRecyclerView();

        return view;
    }

    private void setupRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        rvPhotoList.setLayoutManager(linearLayoutManager);
        rvPhotoList.setAdapter(adapter);
    }

    @Override
    public void showPhotos(List<Photo> photoList) {
        if (photoList != null && photoList.size() > 0) {
            Timber.i("size BIGGER than zero");
            adapter.addItems(photoList);
        } else {
            Timber.i("SIZE IS ZERO");
        }
    }
}
