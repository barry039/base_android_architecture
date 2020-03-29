package com.barry.baseandroidarchitecture.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;

import com.barry.baseandroidarchitecture.viewmodel.BaseViewModel;

public class MainViewModel extends BaseViewModel {

    public MainViewModel(@NonNull Application application) {
        super(application);
        Log.e(TAG,"created");
    }

}
