package com.pxp200.krakenapp.model;

/**
 * Created by fabio.gottlicher on 11/18/17.
 */

public class Building {

    protected double latitude;
    protected double longitude;
    protected String id;
    protected String staticId;


    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
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
}
