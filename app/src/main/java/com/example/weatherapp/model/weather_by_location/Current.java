package com.example.weatherapp.model.weather_by_location;

import com.example.weatherapp.model.Weather;

import java.util.List;

public class Current {
    private Long sunrise;
    private Long sunset;
    private double temp;
    private List<Weather> weather;

    public Current(Long sunrise, Long sunset, double temp, List<Weather> weather) {
        this.sunrise = sunrise;
        this.sunset = sunset;
        this.temp = temp;
        this.weather = weather;
    }

    public Long getSunrise() {
        return sunrise;
    }

    public Long getSunset() {
        return sunset;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public double getTemp() {
        return temp;
    }
}
