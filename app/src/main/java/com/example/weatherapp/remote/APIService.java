package com.example.weatherapp.remote;



import com.example.weatherapp.model.WeatherModel;


import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIService {
    @GET("weather")
    Observable<WeatherModel> getWeatherModel(@Query("q") String q , @Query("appid") String apiid);
}
