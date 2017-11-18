package com.pxp200.krakenapp.model;

/**
 * Created by fabio.gottlicher on 11/17/17.
 */

public class UserResponse extends User {
    private int id;

    public UserResponse(String name) {
        super(name);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
