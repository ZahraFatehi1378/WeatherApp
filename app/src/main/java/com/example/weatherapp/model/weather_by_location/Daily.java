package com.example.weatherapp.model.weather_by_location;

import com.example.weatherapp.model.Weather;

import java.util.List;

public class Daily {
    private Long sunrise;
    private Long sunset;
    private List<Weather> weather;
    private Temp temp;

    public Daily(Long sunrise, Long sunset, List<Weather> weather, Temp temp) {
        this.sunrise = sunrise;
        this.sunset = sunset;
        this.weather = weather;
        this.temp = temp;
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

    public Temp getTemp() {
        return temp;
    }
}
