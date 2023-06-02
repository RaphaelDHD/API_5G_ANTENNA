package com.example.tp_6.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.preference.PreferenceFragmentCompat;

import com.example.tp_6.R;

public class SettingsActivity extends AppCompatActivity {
    boolean isDarkTheme;
    Intent myIntent;
    final int RESULT_CODE = 250;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myIntent = getIntent();
        setContentView(R.layout.settings_activity);
        Log.e("test", "redefine");
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        int highScore = sharedPref.getInt("Theme", AppCompatDelegate.getDefaultNightMode());
        if (highScore == AppCompatDelegate.MODE_NIGHT_YES){
            isDarkTheme = true;
        }
        else {
            isDarkTheme = false;
        }
        Switch switchDayLight = findViewById(R.id.switchDayLight);
        switchDayLight.setChecked(isDarkTheme);

        switchDayLight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleTheme();
            }
        });
    }

    private void toggleTheme() {
        if (isDarkTheme) {
            Log.e("test", "vrai");
            SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putInt("Theme", AppCompatDelegate.MODE_NIGHT_NO);
            editor.apply();
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            isDarkTheme = !isDarkTheme;
        } else {
            Log.e("test", "faux");
            SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putInt("Theme", AppCompatDelegate.MODE_NIGHT_YES);
            editor.apply();
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            isDarkTheme = !isDarkTheme;
        }
        recreate();
    }

}