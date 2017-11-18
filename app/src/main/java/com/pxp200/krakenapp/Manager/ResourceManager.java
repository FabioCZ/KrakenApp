package com.pxp200.krakenapp.Manager;

import java.util.Map;

/**
 * Created by HayzBomb on 11/17/2017.
 */

public class ResourceManager {

    public class state{
        class resource;
        class building;
        class upgrade;
    }
    public state getState(){
        state oldState;


        return oldState;
    }
    public void resourceUpdate(long delta){

        int currentResource;
        int newTotalResource;
        int buildingMultiplier = 1;//per second
        int upgradeMultiplier = 1;//per second
        state oldState;

        oldState = getState();

        buildingMultiplier = oldState.building
        upgradeMultiplier =
        currentResource =

        newTotalResource = currentResource + (delta * buildingMultiplier * upgradeMultiplier);


    }
}
