package com.pxp200.krakenapp.Manager;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.pxp200.krakenapp.KrakenApplication;
import com.pxp200.krakenapp.Storage.LastOpenedPreference;
import com.pxp200.krakenapp.model.BuildingInfo;
import com.pxp200.krakenapp.model.Resource;
import com.pxp200.krakenapp.model.UserResponse;

import java.util.ArrayList;
import java.util.Date;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * Created by HayzBomb on 11/17/2017.
 */

public class Manager extends Service {

    Thread thread;

    long lastServerUpdate;
    long lastUpdate;

    public ArrayList<Resource> staticResources;
    public ArrayList<BuildingInfo> staticBuildings;

    public UserResponse user;
    public boolean userSet = false;

    @Override
    public void onCreate() {
        super.onCreate();
        KrakenApplication.setManager(this.getBaseContext(), this);
        lastServerUpdate = Integer.MAX_VALUE;
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                new Timer().scheduleAtFixedRate(new TimerTask() {
                    @Override
                    public void run() {
                        if(!userSet) {
                            return;
                        }

                        long now = System.currentTimeMillis();
                        long delta = now - lastUpdate;
                        lastUpdate = now;

                        LastOpenedPreference.setLastOpenDate(Manager.this.getBaseContext(), new Date(lastUpdate));
                        if(now + 15000 < lastServerUpdate) {
                            lastServerUpdate = now;
                            syncWithServer();
                        }
                        update(delta);
                    }
                }, 0, 1000); //update resources
            }
        });
        thread.start();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }

    public void setInitialUser(UserResponse user) {
        if(user.getResources() == null || user.getResources().size() == 0) {
            HashMap<String, Double> res = new HashMap<>();
            for(Resource r : staticResources) {
                res.put(r.getId(), 0d);
            }
            user.setResources(res);
        }

        if(user.getBuildings() == null) {
            user.setBuildings(new ArrayList<String>());
        }

        resume();
        userSet = true;
    }
    //updates periodically to apply new changes to the server
    public void syncWithServer() {
        KrakenApplication.getKrakenApi(this.getBaseContext());
    }

    //when you close the app and reopen and accrue resources
    public void resume() {
        long prev = LastOpenedPreference.getLastOpenDate(this.getBaseContext()).getTime(); //pulls from when app closes returns a date
        long now = System.currentTimeMillis();
        long closedTime = now - prev;

        update(closedTime);
    }


    public void update(long delta) {
    }
}