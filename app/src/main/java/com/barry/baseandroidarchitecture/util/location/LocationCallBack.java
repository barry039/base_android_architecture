package com.barry.baseandroidarchitecture.util.location;

import android.location.Location;

public interface LocationCallBack {
    void onLocationReceived(Location location);
    void onLocationDisable();
    void onLocationPermissionDiened();
}
