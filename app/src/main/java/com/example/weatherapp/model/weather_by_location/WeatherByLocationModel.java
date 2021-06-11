package com.example.weatherapp.model.weather_by_location;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherByLocationModel {
    @SerializedName("lat")
    private double lat;
    @SerializedName("lon")
    private double lon;
    @SerializedName("timezone")
    private String timezone;
    @SerializedName("current")
    private Current current;
    @SerializedName("daily")
    private List<Daily> daily;

    public WeatherByLocationModel(double lat, double lon, String timezone, Current current, List<Daily> daily) {
        this.lat = lat;
        this.lon = lon;
        this.timezone = timezone;
        this.current = current;
        this.daily = daily;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    public String getTimezone() {
        return timezone;
    }

    public Current getCurrent() {
        return current;
    }

    public List<Daily> getDaily() {
        return daily;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }
}
