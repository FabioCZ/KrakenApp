package com.pxp200.krakenapp.model;


import java.util.Map;

/**
 * Created by fabio.gottlicher on 11/17/17.
 */

public class Upgrade {
    private Map<String, Integer> cost;
    private String name;
    private String description;
    private String imageUrl;
    private Map<String, Double> multiplies;

    public Map<String, Integer> getCost() {
        return cost;
    }

    public void setCost(Map<String, Integer> cost) {
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Map<String, Double> getMultiplies() {
        return multiplies;
    }

    public void setMultiplies(Map<String, Double> multiplies) {
        this.multiplies = multiplies;
    }
}
