package com.example.weatherapp.model;

public class Main {
    private double temp;
    private int pressure;
    private double temp_min;
    private double temp_man;

    public Main(double temp, int pressure, double temp_min, double temp_man) {
        this.temp = temp;
        this.pressure = pressure;
        this.temp_min = temp_min;
        this.temp_man = temp_man;
    }

    public double getTemp() {
        return temp;
    }

    public int getPressure() {
        return pressure;
    }

    public double getTemp_min() {
        return temp_min;
    }

    public double getTemp_man() {
        return temp_man;
    }
}
