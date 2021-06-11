package com.example.weatherapp.model.weather_by_place;

import com.example.weatherapp.model.Weather;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherByPlaceNameModel {
    @SerializedName("coord")
    private Coord coord;
    @SerializedName("weather")
    private List<Weather> weather;
    @SerializedName("base")
    private String base;
    @SerializedName("main")
    private Main main;
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;


    public WeatherByPlaceNameModel(Coord coord, List<Weather> weather, String base, Main main, int id, String name) {
        this.coord = coord;
        this.weather = weather;
        this.base = base;
        this.main = main;
        this.id = id;
        this.name = name;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public String getBase() {
        return base;
    }

    public Main getMain() {
        return main;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coord getCoord() {
        return coord;
    }
}