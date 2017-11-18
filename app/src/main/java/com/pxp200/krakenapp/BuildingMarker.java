package com.pxp200.krakenapp;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by zac on 11/17/17.
 */

public class BuildingMarker {
    public String title;
    public String imgUrl;
    public double latitude;
    public double longitude;

    public BuildingMarker(String title, double latitude, double longitude) {
        this.title = title;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public MarkerOptions getMarkerOptions() {
        return new MarkerOptions().position(new LatLng(latitude, longitude)).title(title);
    }
}
