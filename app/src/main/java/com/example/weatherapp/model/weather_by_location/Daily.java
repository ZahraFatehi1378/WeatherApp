package com.example.weatherapp.model.weather_by_location;

import com.example.weatherapp.model.Weather;

import java.util.List;

public class Daily {
    private Long sunrise;
    private Long sunset;
    private List<Weather> weather;

    public Daily(Long sunrise, Long sunset, List<Weather> weather) {
        this.sunrise = sunrise;
        this.sunset = sunset;
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
}
