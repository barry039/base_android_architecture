package com.barry.baseandroidarchitecture.activities;

import androidx.lifecycle.ViewModelProvider;

import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import com.barry.baseandroidarchitecture.R;
import com.barry.baseandroidarchitecture.activities.BaseActivity;
import com.barry.baseandroidarchitecture.application.AppApplication;
import com.barry.baseandroidarchitecture.viewmodel.MainViewModel;

public class MainActivity extends BaseActivity {

    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // bind view
        bindView();
        // init var
        init();
    }

    // setup layout bind
    @Override
    protected void bindView() {

    }

    // setup init var
    @Override
    protected void init()
    {
        viewModel = new ViewModelProvider(this,new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(MainViewModel.class);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
