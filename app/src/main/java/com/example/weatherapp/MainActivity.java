package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.weatherapp.model.view_models.WeatherByLocationViewModel;
import com.example.weatherapp.model.weather_by_location.Daily;
import com.example.weatherapp.remote.APIService;
import com.example.weatherapp.remote.RetrofitClass;
import com.example.weatherapp.ui.day.Sun;
import com.example.weatherapp.ui.night.SpiralView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TextView city, temp, status;
    private ConstraintLayout bg;
    private List<Daily> futureDays;
    private RecyclerAdaptor recyclerAdaptor;
    private ImageView imageView;

    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        init();
        WeatherByLocationViewModel viewModel = new ViewModelProvider(this).get(WeatherByLocationViewModel.class);

        viewModel.getWeatherByPlaceModel("tehran", "332c0626ca316418dac6748b502c05e4")
                .observe(this, weatherByPlaceNameModel -> {
                    city.setText(weatherByPlaceNameModel.getName());


                    viewModel.getWeatherByLocationModel(weatherByPlaceNameModel.getCoord().getLon(), weatherByPlaceNameModel.getCoord().getLat(), "minutely,alerts,hourly", "332c0626ca316418dac6748b502c05e4")
                            .observe(this, weatherByLocationModel -> {
                                status.setText(weatherByLocationModel.getCurrent().getWeather().get(0).getDescription());
                                setIcon(weatherByLocationModel.getCurrent().getWeather().get(0).getDescription());
                                double tempValue = weatherByLocationModel.getCurrent().getTemp() - 273.15;
                                temp.setText(String.format("%.2f", tempValue) + "ºc");

                                futureDays.clear();
                                futureDays.addAll(weatherByLocationModel.getDaily());
                                recyclerAdaptor.notifyDataSetChanged();
                            });

                });

        setBG("d", bg);

    }

    private void init() {
        bg = findViewById(R.id.bg);
        city = findViewById(R.id.city);
        temp = findViewById(R.id.temp);
        status = findViewById(R.id.status);
        imageView = findViewById(R.id.mainState);
        recyclerView = findViewById(R.id.recycler);
        futureDays = new ArrayList<>();
        recyclerAdaptor = new RecyclerAdaptor(futureDays, this);
        recyclerView.setAdapter(recyclerAdaptor);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
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

    @SuppressLint("UseCompatLoadingForDrawables")
    private void setIcon(String description) {
        switch (description) {
            case "clear sky":
                imageView.setImageResource(R.drawable.sun);
                break;
            case "few clouds ":
                imageView.setImageResource(R.drawable.cloud);
                break;
            case "scattered clouds":
                imageView.setImageResource(R.drawable.cloud);
                break;
            case "broken clouds":
                imageView.setImageResource(R.drawable.cloud);
                break;
            case "shower rain":
                imageView.setImageResource(R.drawable.rain);
                break;
            case "rain ":
                imageView.setImageResource(R.drawable.rain);
                break;
            case "thunderstorm ":
                imageView.setImageResource(R.drawable.thunderstorm);
                break;
            case "snow":
                imageView.setImageResource(R.drawable.snow);
                break;
            case "mist":
                imageView.setImageResource(R.drawable.mist);
                break;
        }
        imageView.setColorFilter(Color.WHITE);
    }
}