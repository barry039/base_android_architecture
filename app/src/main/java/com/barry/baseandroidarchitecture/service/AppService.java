package com.barry.baseandroidarchitecture.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class AppService extends Service {
    public AppService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // every start or bind would called
        return START_STICKY;
    }


    /*
    ==================================
    Service Bind Config
    ===================================
     */
    @Override
    public IBinder onBind(Intent intent) {
        return iBinder;
    }

    private final IBinder iBinder = new ServiceBinder();

    public class ServiceBinder extends Binder {
        public AppService getService()
        {
            return AppService.this;
        }
    }
}
