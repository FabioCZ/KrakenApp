package com.pxp200.krakenapp.model;

/**
 * Created by fabio.gottlicher on 11/17/17.
 */

public class Resource {
    private String name;
    private String description;
    private int value;
    private String imageUrl;


    public Resource(String name, String description, int value, String imageUrl) {
        this.name = name;
        this.description = description;
        this.value = value;
        this.imageUrl = imageUrl;
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

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
