package com.barry.baseandroidarchitecture.activities;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.Application;
import android.os.Bundle;
import android.util.Log;

import com.barry.baseandroidarchitecture.R;
import com.barry.baseandroidarchitecture.activities.BaseActivity;
import com.barry.baseandroidarchitecture.application.AppApplication;
import com.barry.baseandroidarchitecture.db.DataModel;
import com.barry.baseandroidarchitecture.viewmodel.MainViewModel;

import java.util.List;

public class MainActivity extends BaseActivity {

    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
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
        // init viewmodel
        viewModel = new ViewModelProvider(this,new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(MainViewModel.class);
        // observer data from viewmodel
        observerData();
    }

    private void observerData() {
        viewModel.getLiveData_DataModel().observe(this, new Observer<List<DataModel>>() {
            @Override
            public void onChanged(List<DataModel> dataModels) {
                Log.e(TAG,String.valueOf(dataModels.size()));
            }
        });
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
