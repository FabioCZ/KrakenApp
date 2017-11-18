package com.pxp200.krakenapp.Storage;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.Date;

/**
 * Created by zac on 11/17/17.
 */

public class LastOpenedPreference {
    public static final String LAST_OPEN_DATE_KEY = "LAST_OPEN_DATE_KEY";

    public static Date getLastOpenDate(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return new Date(sharedPreferences.getLong(LAST_OPEN_DATE_KEY, new Date().getTime())); //defaults to now
    }

    public static void setLastOpenDate(Context context, Date lastOpenDate) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        sharedPreferences.edit().putLong(LAST_OPEN_DATE_KEY, lastOpenDate.getTime()).apply();
    }
}
