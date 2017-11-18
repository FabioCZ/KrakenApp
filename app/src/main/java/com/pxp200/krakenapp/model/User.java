package com.pxp200.krakenapp.model;

/**
 * Created by fabio.gottlicher on 11/17/17.
 */

public class User {
    private String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
