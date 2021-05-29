package com.example.weatherapp.ui;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

public class LightLine extends View {
    private final Path path;
    private Paint paint ;
    private int flag = 0;
    private int goUp;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public LightLine(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        path = new Path();
        paint = new Paint();
        paint.setColor(Color.WHITE);
        startAnimation();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        paint.setStyle(Paint.Style.FILL);

        canvas.drawCircle((float) getWidth()/10 , getHeight() - 10 - goUp , 10 , paint);

        canvas.drawCircle((float) 5*getWidth()/40 ,(float) (7*getHeight()/4 - 15 - 3*goUp/2)/2, 3 , paint);
        canvas.drawCircle((float) 3*getWidth()/20 ,(float) (3*getHeight()/2 - 10 - goUp)/2 , 3 , paint);
        canvas.drawCircle((float) 7*getWidth()/40 ,(float) (5*getHeight()/4 - 5 - goUp/2)/2, 3 , paint);

        canvas.drawCircle((float) 2*getWidth()/10 ,(float) getHeight()/2 , 3  , paint);

        canvas.drawCircle((float) 9*getWidth()/40 , (float) (3*getHeight()/4 + 5 + goUp/2)/2 , 3 , paint);
        canvas.drawCircle((float) 5*getWidth()/20 , (float) (getHeight()/2 + goUp+10)/2 , 3 , paint);
        canvas.drawCircle((float) 11*getWidth()/40 , (float) (getHeight()/4 +15 + 3*goUp/2)/2 , 3 , paint);

        canvas.drawCircle((float) 3*getWidth()/10,  10 + goUp, 10 , paint);

        canvas.drawCircle((float) 13*getWidth()/40 , (float) (getHeight()/4 +15 + 3*goUp/2)/2 , 3 , paint);
        canvas.drawCircle((float) 7*getWidth()/20 , (float) (getHeight()/2 + goUp+10)/2 , 3 , paint);
        canvas.drawCircle((float) 15*getWidth()/40 , (float) (3*getHeight()/4 + 5 + goUp/2)/2 , 3 , paint);

        canvas.drawCircle((float) 4*getWidth()/10, (float) getHeight()/2, 3 , paint);

        canvas.drawCircle((float) 17*getWidth()/40 ,(float) (5*getHeight()/4 - 5 - goUp/2)/2, 3 , paint);
        canvas.drawCircle((float) 9*getWidth()/20 , (float) (3*(getHeight() - 10)/4 - goUp/2), 3 , paint);
        canvas.drawCircle((float) 19*getWidth()/40 ,(float) (7*getHeight()/4 - 15 - 3*goUp/2)/2, 3 , paint);

        canvas.drawCircle((float) 5*getWidth()/10, getHeight() - 10 - goUp , 10 , paint);

        canvas.drawCircle((float) 21*getWidth()/40 ,(float) (7*getHeight()/4 - 15 - 3*goUp/2)/2, 3 , paint);
        canvas.drawCircle((float) 11*getWidth()/20 ,(float) (3*getHeight()/2 - 10 - goUp)/2 , 3 , paint);
        canvas.drawCircle((float) 23*getWidth()/40 ,(float) (5*getHeight()/4 - 5 - goUp/2)/2, 3 , paint);

        canvas.drawCircle((float) 6*getWidth()/10 ,(float) getHeight()/2 , 3  , paint);

        canvas.drawCircle((float) 25*getWidth()/40 , (float) (3*getHeight()/4 + 5 + goUp/2)/2 , 3 , paint);
        canvas.drawCircle((float) 13*getWidth()/20 , (float) (getHeight()/2 + goUp+10)/2 , 3 , paint);
        canvas.drawCircle((float) 27*getWidth()/40 , (float) (getHeight()/4 +15 + 3*goUp/2)/2 , 3 , paint);

        canvas.drawCircle((float) 7*getWidth()/10,  10 + goUp, 10 , paint);

        canvas.drawCircle((float) 29*getWidth()/40 , (float) (getHeight()/4 +15 + 3*goUp/2)/2 , 3 , paint);
        canvas.drawCircle((float) 15*getWidth()/20 , (float) (getHeight()/2 + goUp+10)/2 , 3 , paint);
        canvas.drawCircle((float) 31*getWidth()/40 , (float) (3*getHeight()/4 + 5 + goUp/2)/2 , 3 , paint);

        canvas.drawCircle((float) 8*getWidth()/10, (float) getHeight()/2, 3 , paint);

        canvas.drawCircle((float) 33*getWidth()/40 ,(float) (5*getHeight()/4 - 5 - goUp/2)/2, 3 , paint);
        canvas.drawCircle((float) 17*getWidth()/20 , (float) (3*(getHeight() - 10)/4 - goUp/2), 3 , paint);
        canvas.drawCircle((float) 35*getWidth()/40 ,(float) (7*getHeight()/4 - 15 - 3*goUp/2)/2, 3 , paint);

        canvas.drawCircle((float) 9*getWidth()/10, getHeight() - 10 - goUp , 10 , paint);

        canvas.drawPath(path , paint);

    }


    @SuppressLint("WrongConstant")
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void startAnimation() {

        //for circle
        ValueAnimator animator = ValueAnimator.ofFloat(0, 80);
        animator.setDuration(4000);

        animator.addUpdateListener(valueAnimator -> {
            goUp = ((Float) valueAnimator.getAnimatedValue()).intValue();
            invalidate();
        });
        animator.setRepeatCount(Animation.INFINITE);
        animator.setRepeatMode(Animation.REVERSE);
        animator.start();

    }



//    ValueAnimator.AnimatorUpdateListener{
//        @Override
//        public void onAnimationUpdate(ValueAnimator animation) {
//            //Do something
//        }


//    ValueAnimator animation= ValueAnimator.ofInt();
//    Animator.AnimatorListener
//    animation.setInterpolator(new LinearInterpolator());
//    animation.setDuration(mShortAnimationDuration);
//    animation.start();
}
