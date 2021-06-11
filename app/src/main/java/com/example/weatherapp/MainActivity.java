package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.weatherapp.remote.APIService;
import com.example.weatherapp.remote.RetrofitClass;
import com.example.weatherapp.ui.day.Sun;
import com.example.weatherapp.ui.night.SpiralView;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {


    CompositeDisposable compositeDisposable;
    private String url = "https://samples.openweathermap.org/data/2.5/";
    private String url1 = "https://api.openweathermap.org/data/2.5/";
    private APIService request;
    private APIService request1;
    private TextView city, temp, status;
    private ConstraintLayout bg;
    private double lat, lon;


    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        compositeDisposable.add(request1.getWeatherByPlaceModel("tehran", "332c0626ca316418dac6748b502c05e4")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(weatherModel -> {
                    city.setText(weatherModel.getName());
                    status.setText(weatherModel.getWeather().get(0).getMain());
                    double tempValue = weatherModel.getMain().getTemp() - 273.15;
                    temp.setText(String.format("%.2f", tempValue));
                    lat = weatherModel.getCoord().getLat();
                    lon = weatherModel.getCoord().getLon();

                    compositeDisposable.add(request.getWeatherByLocation(lat, lon, "minutely,alerts,hourly", "332c0626ca316418dac6748b502c05e4")
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribeOn(Schedulers.io())
                            .subscribe(weatherByLocationModel -> {
                                System.out.println(weatherByLocationModel.getDaily().get(0).getWeather().get(0).getMain());
                            }));
                }));

        setBG("d", bg);

    }

    private void init() {
        request = RetrofitClass.getApi(url1).create(APIService.class);
        request1 = RetrofitClass.getApi(url).create(APIService.class);
        compositeDisposable = new CompositeDisposable();
        bg = findViewById(R.id.bg);
        city = findViewById(R.id.city);
        temp = findViewById(R.id.temp);
        status = findViewById(R.id.status);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }


    @SuppressLint("UseCompatLoadingForDrawables")
    private void setBG(String state, ConstraintLayout bg) {
        Sun sun = findViewById(R.id.sun);
        SpiralView spiralView2 = findViewById(R.id.spiralView2);

        if (state.equals("d")) {
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