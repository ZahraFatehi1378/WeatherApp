package com.example.weatherapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.weatherapp.dialog.CustomDialog;
import com.example.weatherapp.model.view_models.WeatherByLocationViewModel;
import com.example.weatherapp.model.weather_by_location.Daily;
import com.example.weatherapp.remote.APIService;
import com.example.weatherapp.remote.RetrofitClass;
import com.example.weatherapp.ui.day.Sun;
import com.example.weatherapp.ui.night.SpiralView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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
    private ImageView theme, setting;
    private SharedPreferencesClass sharedPreferencesClass;


    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        getSupportActionBar().hide();
        sharedPreferencesClass = new SharedPreferencesClass();
        sharedPreferencesClass.getThemeOnMainActivityCreate(this);
        Utils.onActivityCreateThemeForMainActivity(this);

        init();
        sendRequest("tehran");
        setBG(bg);

    }

    private void sendRequest( String cityName){
        WeatherByLocationViewModel viewModel = new ViewModelProvider(this).get(WeatherByLocationViewModel.class);
        viewModel.getWeatherByPlaceModel(cityName, "332c0626ca316418dac6748b502c05e4")
                .observe(this, weatherByPlaceNameModel -> {
                    city.setText(weatherByPlaceNameModel.getName());
                    viewModel.getWeatherByLocationModel(weatherByPlaceNameModel.getCoord().getLat(), weatherByPlaceNameModel.getCoord().getLon(), "minutely,alerts,hourly", "332c0626ca316418dac6748b502c05e4")
                            .observe(this, weatherByLocationModel -> {
                                System.out.println(weatherByLocationModel.getCurrent().getTemp());
                                status.setText(weatherByLocationModel.getCurrent().getWeather().get(0).getDescription());
                                setIcon(weatherByLocationModel.getCurrent().getWeather().get(0).getDescription());
                                double tempValue = weatherByLocationModel.getCurrent().getTemp() - 273.15;
                                temp.setText(String.format("%.2f", tempValue) + "ºc");

                                futureDays.clear();
                                futureDays.addAll(weatherByLocationModel.getDaily());
                                //     Collections.reverse(futureDays);
                                recyclerAdaptor.notifyDataSetChanged();
                            });
                });
    }

    private void init() {
        bg = findViewById(R.id.bg);
        city = findViewById(R.id.city);
        temp = findViewById(R.id.temp);
        status = findViewById(R.id.status);
        imageView = findViewById(R.id.mainState);
        recyclerView = findViewById(R.id.recycler);
        theme = findViewById(R.id.theme);
        setting = findViewById(R.id.setting);
        futureDays = new ArrayList<>();
        recyclerAdaptor = new RecyclerAdaptor(futureDays, this);
        recyclerView.setAdapter(recyclerAdaptor);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        addListener();
    }


    @SuppressLint("UseCompatLoadingForDrawables")
    private void setBG(ConstraintLayout bg) {
        if (Utils.sTheme.equals("dark")) {
            theme.setImageResource(R.drawable.ic_sun);
        } else {
            theme.setImageResource(R.drawable.ic_night_mode);
        }

        Sun sun = findViewById(R.id.sun);
        SpiralView spiralView2 = findViewById(R.id.spiralView2);

        if (Utils.sTheme.equals("dark")) {
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

    private void addListener() {
        theme.setOnClickListener(v -> {
            if (Utils.sTheme.equals("bright")) {
                changeThemeToDark();
            } else if (Utils.sTheme.equals("dark")) {
                changeThemeToBright();
            }
        });

        setting.setOnClickListener(v -> {
            CustomDialog customDialog = new CustomDialog(new CustomDialog.OnSaveListener() {
                @Override
                public void saved(String cityName) {
                    sendRequest(cityName);
                }
            });
            customDialog.showDialog(MainActivity.this);

        });

    }

    private void changeThemeToDark() {
        Utils.changeToTheme(this, "dark");
        sharedPreferencesClass.changeTheTheme(this);
    }

    private void changeThemeToBright() {
        Utils.changeToTheme(this, "bright");
        sharedPreferencesClass.changeTheTheme(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.FROYO)
    @SuppressLint("UseCompatLoadingForDrawables")
    private void setIcon(String description) {

        if (description.contains("clear sky")) {
            imageView.setImageResource(R.drawable.sun);
        } else if (description.contains("few clouds")) {
            imageView.setImageResource(R.drawable.cloud);
        } else if (description.contains("scattered clouds")) {
            imageView.setImageResource(R.drawable.cloud);
        } else if (description.contains("broken clouds")) {
            imageView.setImageResource(R.drawable.cloud);
        } else if (description.contains("shower rain")) {
            imageView.setImageResource(R.drawable.rain);
        } else if (description.contains("rain")) {
            imageView.setImageResource(R.drawable.rain);
        } else if (description.contains("thunderstorm")) {
            imageView.setImageResource(R.drawable.thunderstorm);
        } else if (description.contains("snow")) {
            imageView.setImageResource(R.drawable.snow);
        } else if (description.contains("mist")) {
            imageView.setImageResource(R.drawable.mist);
        }

        imageView.setColorFilter(Color.WHITE);
    }
}