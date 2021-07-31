package com.example.weatherapp;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesClass {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;


    public void changeTheTheme(Activity activity) {
        sharedPreferences = activity.getPreferences(Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putString("", Utils.sTheme).apply();
    }

    public void getThemeOnMainActivityCreate(Activity activity) {
        SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
        Utils.sTheme = sharedPref.getString("", "dark");
    }

}