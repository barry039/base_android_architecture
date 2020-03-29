package com.barry.baseandroidarchitecture.activities;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.IBinder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.barry.baseandroidarchitecture.service.AppService;

public abstract class BaseActivity extends AppCompatActivity {

    protected String TAG = this.getClass().getSimpleName();

    /*
    ==================================
    固定常用 func設置
    ===================================
    */
    abstract void bindView();

    abstract void init();

    /*
    ==================================
    Permission func
    ===================================
    */

    protected boolean checkPermission(String permission)
    {
        return ActivityCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED;
    }

    protected void requestPermission(String[] permissions)
    {
        ActivityCompat.requestPermissions(this, permissions, 1001);
    }

    /*
    ==================================
    Service綁定  綁定週期:onCreated->onDestroy or onStart->onStop
    ===================================
     */
    protected void bindService()
    {
        Intent intentService = new Intent(this, AppService.class);

        startService(intentService);

        bindService(intentService, serviceConnect, Context.BIND_AUTO_CREATE);
    }

    protected void unBindService()
    {
        if(isBinded)
        {
            unbindService(serviceConnect);
        }
    }

    protected boolean isBinded = false;

    protected ServiceConnection serviceConnect = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            AppService.ServiceBinder binder = (AppService.ServiceBinder) service;
            AppService appService = binder.getService();
            isBinded = true;
            // do something after binded service
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
}
