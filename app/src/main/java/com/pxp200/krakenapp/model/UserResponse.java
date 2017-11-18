package com.pxp200.krakenapp.model;

/**
 * Created by fabio.gottlicher on 11/17/17.
 */

public class UserResponse {
    private String name;
    private int id;

    public UserResponse(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
