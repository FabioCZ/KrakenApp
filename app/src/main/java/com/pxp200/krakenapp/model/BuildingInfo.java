package com.pxp200.krakenapp.model;

import java.util.ArrayList;

/**
 * Created by fabio.gottlicher on 11/18/17.
 */

public class BuildingInfo {
    protected String id;
    protected ArrayList<Resource> produces;
    protected ArrayList<Resource> consumes;
    protected String name;

    public BuildingInfo(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<Resource> getProduces() {
        return produces;
    }

    public void setProduces(ArrayList<Resource> produces) {
        this.produces = produces;
    }

    public ArrayList<Resource> getConsumes() {
        return consumes;
    }

    public void setConsumes(ArrayList<Resource> consumes) {
        this.consumes = consumes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProducesString() {
        return "Gold 12\nSilver 10";
    }

    public String getConsumesString() {
        return "Pizza 10\nWood 6";
    }
}
