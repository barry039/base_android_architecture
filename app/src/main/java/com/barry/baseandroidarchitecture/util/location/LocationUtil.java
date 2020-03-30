package com.barry.baseandroidarchitecture.util.location;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.core.app.ActivityCompat;

public class LocationUtil {
    private Context context;
    private LocationManager locationManager = null;
    private LocationCallBack locationCallBack = null;
    public static final String LOCATION_GPS = "GPS";
    public static final String LOCATION_NETWORK = "NETWORK";
    private static final String TAG = "DEBUG";
    private static final String[] provider = new String[]
            {
                    LocationManager.GPS_PROVIDER,
                    LocationManager.NETWORK_PROVIDER,
                    LocationManager.PASSIVE_PROVIDER
            };

    public LocationUtil(Context context, LocationCallBack locationCallBack) {
        this.context = context;
        this.locationCallBack = locationCallBack;
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
    }

    // get last know locaton from all provider
    public Location getLocationByProvider(Context context) {
        Location location = null;
        // LocationManager locationManager = (LocationManager) context.getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        try {
            for (int i = 0; i < provider.length; i++) {
                assert locationManager != null;
                if (locationManager.isProviderEnabled(provider[i])) {
                    // locationManager.requestLocationUpdates(provider[i], 0, 0, locationListenerGps);
                    if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        return null;
                    }
                    location = locationManager.getLastKnownLocation(provider[i]);
                    if (location != null)
                    {
                        break;
                    }
                }
            }
        }
        catch (IllegalArgumentException e)
        {
            Log.e(TAG, "Cannot access Provider " + provider);
        }
        return location;
    }

    // get location by request location update. asynchronous way
    public void getLocation() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (context.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && context.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, network_locationListener);
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,gps_locationListener);
                return;
            }else
            {
                locationCallBack.onLocationPermissionDiened();
            }
        }
    }

    private boolean checkNetWorkEnable()
    {
        if(locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER))
        {
            return true;
        }
        locationCallBack.onLocationDisable();
        return false;
    }

    private boolean checkGPSEnable()
    {
        if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER))
        {
            return true;
        }
        locationCallBack.onLocationDisable();
        return false;
    }

    private LocationListener network_locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            if (location != null) {
                //received location, remove listener soon.
                locationCallBack.onLocationReceived(location);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (context.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && context.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        Location last_location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                        if(last_location != null)
                        {
                            Log.e("network last location",String.valueOf(last_location.getLatitude()) + "," + String.valueOf(last_location.getLongitude()));
                        }
                    }
                }
            }
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };

    private LocationListener gps_locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            if (location != null) {
                locationCallBack.onLocationReceived(location);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (context.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && context.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        Location last_location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                        if(last_location != null)
                        {
                            Log.e("gps last location",String.valueOf(last_location.getLatitude()) + "," + String.valueOf(last_location.getLongitude()));
                        }
                    }
                }
            }
        }
        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };

    public void removeListener()
    {
        if(locationManager != null)
        {
            locationManager.removeUpdates(network_locationListener);
            locationManager.removeUpdates(gps_locationListener);
        }
    }

    public void onDestory()
    {
        //remove listener
        removeListener();
        // clear instance
        context = null;
        locationCallBack = null;
        locationManager = null;
    }
}
