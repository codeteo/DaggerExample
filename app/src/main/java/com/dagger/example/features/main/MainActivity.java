package com.dagger.example.features.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dagger.example.R;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fl_container, MainFragment.newInstance())
                .commit();

    }
}
