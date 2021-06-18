package com.example.weatherapp.remote;

import com.example.weatherapp.model.weather_by_location.WeatherByLocationModel;
import com.example.weatherapp.model.weather_by_place.WeatherByPlaceNameModel;

import io.reactivex.rxjava3.core.Observable;

public class Repository {

    public Observable<WeatherByPlaceNameModel> getWeatherByPlace(String place , String key){
        APIService request = RetrofitClass.getRetrofit().create(APIService.class);
        return request.getWeatherByPlaceModel(place , key);
    }

    public Observable<WeatherByLocationModel> getWeatherByLocation(double lat , double lon , String excludes , String key ){
        APIService request=  RetrofitClass.getRetrofit().create(APIService.class);
        return request.getWeatherByLocation(lat , lon , excludes , key );
    }

}
