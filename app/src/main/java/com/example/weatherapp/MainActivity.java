package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.example.weatherapp.ui.day.Sun;
import com.example.weatherapp.ui.night.SpiralView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        ConstraintLayout bg = findViewById(R.id.bg);
        setBG("d", bg);

    }


    private void setBG(String state, ConstraintLayout bg) {

        Sun sun = findViewById(R.id.sun);
        SpiralView spiralView2 = findViewById(R.id.spiralView2);

        if (state == "d") {
            bg.setBackground(getResources().getDrawable(R.drawable.day));
            sun.setVisibility(View.VISIBLE);
            spiralView2.setVisibility(View.GONE);
        } else {
            sun.setVisibility(View.GONE);
            spiralView2.setVisibility(View.VISIBLE);
            bg.setBackground(getResources().getDrawable(R.drawable.night));
            spiralView2.setSpiralRadius(450f);
            spiralView2.setSpiralRotation(90f);
            spiralView2.setSpacesBetweenCircles(3);

            //  spiralView2.setCoils(2f);
        }

    }
}