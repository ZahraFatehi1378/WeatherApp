package com.example.weatherapp.ui.night;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.weatherapp.ui.night.Circle;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;


public class SpiralView extends View {

    private Path path;
    private Paint paint;
    private int circleColor = Color.parseColor("#adcbe3");
    private int mainColor = Color.parseColor("#1e1f26");
    private int signColor = Color.parseColor("#011f4b");
    private int flag = 0;
    private final Long TimeToGetNewCircle = 1000L;
    private final Long circleLifeTime = 30000L;
    private Long TimeToGetNewRandomCircle = 2000L;
    private Long MIN_RANDOM_CIRCLE_LIFE_TIME = 7000L;
    private Long MAX_RANDOM_CIRCLE_LIFE_TIME = 10000L;
    private final int maxRandomCircleRadius = 10;
    private Float w = 0f;
    private Float h = 0f;
    private final Float maxCircleRadius = 30f;
    private int spacesBetweenCircles = 50;
    private ArrayList<Circle> circles;
    private ArrayList<Circle> randomCircles;
    private int flag2 = 0;
    private Shader shader;
    private float coils = 4f;
    private float spiralRadius = 500f;
    private float spiralCenterY = -1000f;
    private float spiralCenterX = -1000f;
    private Random random;
    private float spiralRotation = 50f;

    public SpiralView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        path = new Path();
        paint = new Paint();
        circles = new ArrayList<>();
        randomCircles = new ArrayList<>();
        random = new Random();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (flag == 0) { // just run for first time
            //    startAnimation();
            flag = 1;
            w = (float) getWidth();
            h = (float) getHeight();

            if (spiralCenterX == -1000 && spiralCenterY == -1000) {
                spiralCenterY = h / 2;
                spiralCenterX = w / 2;
            }

        }

        if (circles.size() == 0 || System.currentTimeMillis() - circles.get(circles.size() - 1).getCreatedTime() > TimeToGetNewCircle) {
            addNewCircle(spiralCenterX, spiralCenterY, spiralRadius, coils, spiralRotation);
            if (flag2 == 0) {
                updateCircles(spiralCenterX, spiralCenterY, spiralRadius, coils, spiralRotation);
                flag2 = 1;
            }
        }

        addRandomCircles();
        for (Circle circle : randomCircles) {
            paint.setAlpha(circle.getAlpha());
            shader = new RadialGradient(circle.getX(), circle.getY(), circle.getR(), Color.WHITE, mainColor, Shader.TileMode.CLAMP);
            paint.setShader(shader);
            canvas.drawCircle(circle.getX(), circle.getY(), circle.getR(), paint);
        }


        Iterator<Circle> iterator = circles.iterator();
        while (iterator.hasNext()) {
            Circle circle = iterator.next();
            paint.setAlpha(circle.getAlpha());
            shader = new RadialGradient(circle.getX(), circle.getY(), circle.getR(), Color.WHITE, mainColor, Shader.TileMode.CLAMP);
            paint.setShader(shader);
            canvas.drawCircle(circle.getX(), circle.getY(), circle.getR(), paint);
        }



    }

    private void addRandomCircles() {
        if (randomCircles.size() == 0 || System.currentTimeMillis() - randomCircles.get(randomCircles.size() - 1).getCreatedTime() > TimeToGetNewRandomCircle) {
            int x = (int) w.floatValue();
            int y = (int) h.floatValue();
            randomCircles.add(new Circle(random.nextInt(x), random.nextInt(y), random.nextInt(maxRandomCircleRadius + 2), 255, 0f, System.currentTimeMillis(),
                            MIN_RANDOM_CIRCLE_LIFE_TIME + (long) (random.nextDouble() * (MAX_RANDOM_CIRCLE_LIFE_TIME - MIN_RANDOM_CIRCLE_LIFE_TIME)))
            );

        }


        Iterator<Circle> iterator = randomCircles.iterator();
        while (iterator.hasNext()) {
            Circle circle = iterator.next();
            circle.setAlpha(255 - (int) ((float) (System.currentTimeMillis() - circle.getCreatedTime()) * 255 / circle.getLifeTime()));
            //remove circles
            if (circle.getAlpha() <= 5 || circle.getR() < 1) {
                iterator.remove();
            }

        }
    }

    void addNewCircle(float centerX, float centerY, float radius, float coils, float rotation) {
        float x = 0, y = 0;
        final double thetaMax = coils * 2 * Math.PI;
        final double awayStep = radius / thetaMax;
        final double chord = spacesBetweenCircles;

        //go to last circle location
        for (double theta = chord / awayStep; theta <= thetaMax; ) {
            double away = awayStep * theta;
            double around = theta + rotation;
            x = (float) (centerX + Math.cos(around) * away);
            y = (float) (centerY + Math.sin(around) * away);
            theta += chord / away;
        }
        circles.add(new Circle(x, y, maxCircleRadius, 255, (float) thetaMax, System.currentTimeMillis(), 0L));
    }


    private void updateCircles(float centerX, float centerY, float radius, float coils, float rotation) {
        // for moving
        final float thetaMax = (float) (coils * 2 * Math.PI);
        final float awayStep = radius / thetaMax;
        final float chord = spacesBetweenCircles;//sets speed//todo to be customize
        ValueAnimator movingAnimator = ValueAnimator.ofInt(255, 0);
        movingAnimator.setDuration(1000);//sets distance//todo
        movingAnimator.addUpdateListener(animation -> {

            Iterator<Circle> iterator = circles.iterator();
            while (iterator.hasNext()) {
                Circle circle = iterator.next();
                float away = awayStep * circle.getTheta();
                float around;
                circle.setTheta(circle.getTheta() - chord / away);
                away = awayStep * circle.getTheta();
                around = circle.getTheta() + rotation;

                circle.setX((float) (centerX + Math.cos(around) * away));
                circle.setY((float) (centerY + Math.sin(around) * away));
                //   circle.setAlpha((int) ((float)(System.currentTimeMillis() - circle.getCreatedTime())*255/circleLifeTime));//todo decide later
                circle.setAlpha(255 - (int) ((float) (System.currentTimeMillis() - circle.getCreatedTime()) * 255 / circleLifeTime));

                circle.setR(maxCircleRadius - ((float) (System.currentTimeMillis() - circle.getCreatedTime()) * maxCircleRadius / circleLifeTime));
                //remove circles
                if (circle.getAlpha() <= 5 || circle.getR() < 1) {
                    iterator.remove();
                }
                invalidate();
            }
        });
        movingAnimator.start();
        movingAnimator.setRepeatCount(ValueAnimator.INFINITE);
    }


    void drawSpiral(float centerX, float centerY, float radius, float coils, float rotation, Canvas canvas) {
        // value of theta corresponding to end of last coil
        final double thetaMax = coils * 2 * Math.PI;
        // How far to step away from center for each side.
        final double awayStep = radius / thetaMax;
        // distance between points to plot
        final double chord = spacesBetweenCircles;
        // For every side, step around and away from center.
        // start at the angle corresponding to a distance of chord away from centre.

        for (double theta = chord / awayStep; theta <= thetaMax; ) {
            // How far away from center
            double away = awayStep * theta;
            // How far around the center.
            double around = theta + rotation;
            // Convert 'around' and 'away' to X and Y.
            float x = (float) (centerX + Math.cos(around) * away);
            float y = (float) (centerY + Math.sin(around) * away);
            // Now that you know it, do it.
            canvas.drawCircle(x, y, 5, paint);

            // to a first approximation, the points are on a circle
            // so the angle between them is chord/radius
            theta += chord / away;

        }
    }

    public void setSpiralRotation(float spiralRotation) {
        this.spiralRotation = spiralRotation;
    }

    public void setSpiralCenterY(float spiralCenterY) {
        this.spiralCenterY = spiralCenterY;
    }

    public void setSpiralCenterX(float spiralCenterX) {
        this.spiralCenterX = spiralCenterX;
    }

    public void setCoils(float coils) {
        this.coils = coils;
    }

    public void setSpiralRadius(float spiralRadius) {
        this.spiralRadius = spiralRadius;
    }

    public void setSpacesBetweenCircles(int spacesBetweenCircles) {
        this.spacesBetweenCircles = spacesBetweenCircles;
    }
}


//    private void circleStartMoving(float centerX, float centerY, float radius, float coils, float rotation) {
//        // for moving
//        final float thetaMax = (float) (coils * 2 * Math.PI);
//        final float awayStep = radius / thetaMax;
//        final float chord = 1;//sets speed//todo to be customize
//        final float[] theta = {thetaMax};
//        ValueAnimator movingAnimator = ValueAnimator.ofFloat(100, 0);
//        movingAnimator.setDuration(5000);//sets distance//todo
//        movingAnimator.addUpdateListener(animation -> {
//
//            for (Circle circle : circles) {
//                float away = awayStep * theta[0];
//                float around;
//                theta[0] -= chord / away;
//                away = awayStep * theta[0];
//                around = theta[0] + rotation;
//
//                circle.setX((float) (centerX + Math.cos(around) * away));
//                circle.setY((float) (centerY + Math.sin(around) * away));
//                invalidate();
//            }
//        });
//        movingAnimator.start();
//        movingAnimator.setRepeatCount(ValueAnimator.INFINITE);
//    }
