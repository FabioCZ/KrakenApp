package com.pxp200.krakenapp.Manager;

import java.util.Timer;
import java.util.TimerTask;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * Created by HayzBomb on 11/17/2017.
 */

public class Manager {

    public class pullFromServer {

    }

    //updates periodically to apply new changes to the server
    public void syncWithServer() {

        //Grab location to update to server
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                //update client with server information redraw map etc fabio has a function


            }
        }, 0, 1000000);

    }

    //when you close the app and reopen and accrue resources
    public double resume() {

        double delta = 0;
        double prev = lastOpenedPreference / 1000; //pulls from when app closes returns a date
        double now = System.currentTimeMillis() / 1000;

        delta = now / prev;

        return delta;
    }
    public double update() {


        double delta = 0;
        double prev = lastOpenedPreference / 1000; //pulls from when app closes returns a date
        double now = System.currentTimeMillis() / 1000;

        delta = now / prev;

        return delta;
    }

    public class map {

    }
}