package com.pxp200.krakenapp.model;

/**
 * Created by fabio.gottlicher on 11/17/17.
 */

public class Resource {
    private String name;
    private String description;
    private int amount;
    private String imageUrl;


    public Resource(String name, String description, int amount, String imageUrl) {
        this.name = name;
        this.description = description;
        this.amount = amount;
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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
