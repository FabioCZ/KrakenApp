package com.pxp200.krakenapp.model;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by fabio.gottlicher on 11/18/17.
 */

public class Building {

    protected int latitude;
    protected int longitude;
    protected String id;
    protected String staticId;


    public int getLatitude() {
        return latitude;
    }

    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }

    public int getLongitude() {
        return longitude;
    }

    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStaticId() {
        return staticId;
    }

    public void setStaticId(String staticId) {
        this.staticId = staticId;
    }

    public MarkerOptions getMarkerOptions(BuildingInfo info) {
        return new MarkerOptions().position(new LatLng(latitude, longitude)).title(info.getName());
    }
}
