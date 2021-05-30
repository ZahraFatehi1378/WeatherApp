package com.example.weatherapp.ui.day;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Iterator;

public class Sun extends View {

    private ArrayList<SunCircle> sunCircles;
    private Paint paint;
    private int sunColor = Color.parseColor("#FFCF93");
    private float sunRadius = 12;
    private float circleRadius ;
    private final Long circleLifeTime = 6000L;
    private int flag = 1;

    public Sun(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        sunCircles = new ArrayList<>();
        paint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (flag ==1) {
            addSunCircle();
            circleRadius = 10*getHeight();
            flag = 0;
        }

        makeSunBigger(canvas);
        updateCircles();

    }

    private void addSunCircle() {
        SunCircle sunCircle = new SunCircle(getWidth()/2, getHeight()/2, getWidth()/3, 255, System.currentTimeMillis());
        sunCircles.add(sunCircle);
    }

    private void makeSunBigger(Canvas canvas) {
        paint.setColor(sunColor);
        for (SunCircle sunCircle : sunCircles) {
            paint.setAlpha(sunCircle.getAlpha());
            canvas.drawCircle(sunCircle.getX(), sunCircle.getY(), sunCircle.getR()  / sunRadius, paint);
        }
       // F2C77B
        paint.setAlpha(255);
        paint.setColor(Color.parseColor("#FDD9AC"));
        canvas.drawCircle(getWidth()/2, getHeight()/2, getWidth()/4, paint);
    }

    private void updateCircles() {
        // for moving
        Iterator<SunCircle> iterator = sunCircles.iterator();
        while (iterator.hasNext()) {
            SunCircle circle = iterator.next();
            circle.setAlpha(255 - (int) ((float) (System.currentTimeMillis() - circle.getCreatedTime()) * 255 / circleLifeTime));
            circle.setR(sunRadius + ((float) (System.currentTimeMillis() - circle.getCreatedTime()) * circleRadius / (2 * circleLifeTime)));
            if (circle.getAlpha() <= 5 || circle.getR() >= circleRadius) {
                iterator.remove();
            }
        }
        if (sunCircles.size() != 0)
            if (System.currentTimeMillis() - sunCircles.get(sunCircles.size() - 1).getCreatedTime() > circleLifeTime / 5) {
                addSunCircle();
            }
        invalidate();
    }

}
