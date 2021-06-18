package com.example.weatherapp.model.weather_by_location;

public class Temp {
    private double day;
    private double night;
    private double max;
    private double min;

    public Temp(double day, double night, double max, double min) {
        this.day = day;
        this.night = night;
        this.max = max;
        this.min = min;
    }

    public double getDay() {
        return day;
    }

    public double getNight() {
        return night;
    }

    public double getMax() {
        return max;
    }

    public double getMin() {
        return min;
    }
}
