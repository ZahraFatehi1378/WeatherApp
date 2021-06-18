package com.example.weatherapp.remote;



import com.example.weatherapp.model.weather_by_location.WeatherByLocationModel;
import com.example.weatherapp.model.weather_by_place.WeatherByPlaceNameModel;


import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface APIService {
    @GET("weather")
    Observable<WeatherByPlaceNameModel> getWeatherByPlaceModel(@Query("q") String q , @Query("appid") String apiid);
    //http://api.openweathermap.org/data/2.5/weather?q=paris&appid=332c0626ca316418dac6748b502c05e4

    @GET("onecall")
    Observable <WeatherByLocationModel> getWeatherByLocation
            ( @Query("lat") double lat, @Query("lon") double lon , @Query("exclude") String exclude , @Query("appid") String appid );
    //https://api.openweathermap.org/data/2.5/onecall?lat=33.44&lon=-94.04&exclude=minutely,alerts,hourly&appid=332c0626ca316418dac6748b502c05e4
}
