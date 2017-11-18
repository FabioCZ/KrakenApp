package com.pxp200.krakenapp.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by fabio.gottlicher on 11/17/17.
 */

public class User {
    protected String name;
    protected Map<String, Double> resources;
    protected ArrayList<String> buildings;
    protected Double latitude;
    protected Double longitude;

    public User(String name) {
        this.name = name;
        resources = new HashMap<>();
        buildings = new ArrayList<>();
    }

    public void incrementResource(String resource, Integer cnt){
        //INCREMENT RESOURCE

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Double> getResources() {
        return resources;
    }

    public void setResources(Map<String, Double> resources) {
        this.resources = resources;
    }

    public ArrayList<String> getBuildings() {
        return buildings;
    }

    public void setBuildings(ArrayList<String> buildings) {
        this.buildings = buildings;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongtitude() {
        return longitude;
    }

    public void setLongtitude(Double longitude) {
        this.longitude = longitude;
    }
}
