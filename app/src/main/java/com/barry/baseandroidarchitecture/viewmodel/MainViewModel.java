package com.barry.baseandroidarchitecture.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.barry.baseandroidarchitecture.db.DataModel;
import com.barry.baseandroidarchitecture.db.DataRepository;
import com.barry.baseandroidarchitecture.network.APIManager;
import com.barry.baseandroidarchitecture.viewmodel.BaseViewModel;

import java.util.List;

public class MainViewModel extends BaseViewModel {

    public MainViewModel(@NonNull Application application) {
        super(application);
        Log.e(TAG,"created");
        repository = new DataRepository(application);

        APIManager.getINSTANCE().getAlbum(new APIManager.APIInterface.GetAlbum() {
            @Override
            public void onSuc(String s) {
                Log.e(TAG,s);
            }

            @Override
            public void onError(String message) {
                Log.e(TAG,message);

            }

            @Override
            public void onFail(String message) {
                Log.e(TAG,message);

            }
        });

        APIManager.getINSTANCE().postAlbums("Test1", "Test2", new APIManager.APIInterface.PostAlbum() {
            @Override
            public void onSuc(String s) {
                Log.e(TAG,s);
            }

            @Override
            public void onError(String message) {
                Log.e(TAG,message);

            }

            @Override
            public void onFail(String message) {
                Log.e(TAG,message);

            }
        });
    }
    private DataRepository repository;
    public LiveData<List<DataModel>> getLiveData_DataModel()
    {
        return repository.getAllData();
    }

}
