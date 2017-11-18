package com.pxp200.krakenapp.Storage;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.Date;

/**
 * Created by zac on 11/17/17.
 */

public class UsernamePreference {
    public static final String USERNAME_KEY = "USERNAME_KEY";

    public static boolean exists(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.contains(USERNAME_KEY);
    }
    public static String get(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(USERNAME_KEY, "Jerry"); // sensible default
    }

    public static void set(Context context, String username) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        sharedPreferences.edit().putString(USERNAME_KEY, username).apply();
    }

    public static void clear(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        sharedPreferences.edit().remove(USERNAME_KEY).apply();
    }
}
