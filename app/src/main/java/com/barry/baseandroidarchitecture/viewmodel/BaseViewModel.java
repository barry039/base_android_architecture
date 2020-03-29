package com.barry.baseandroidarchitecture.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

public class BaseViewModel extends AndroidViewModel {

    protected String TAG = this.getClass().getSimpleName();

    public BaseViewModel(@NonNull Application application) {
        super(application);
    }

}
