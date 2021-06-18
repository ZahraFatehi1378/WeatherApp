package com.example.weatherapp.model.view_models;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.weatherapp.model.weather_by_location.WeatherByLocationModel;
import com.example.weatherapp.model.weather_by_place.WeatherByPlaceNameModel;
import com.example.weatherapp.remote.Repository;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class WeatherByLocationViewModel extends ViewModel {

    private MutableLiveData<WeatherByPlaceNameModel> weatherByPlaceModels;
    private MutableLiveData<WeatherByLocationModel> weatherByLocationModel;
    private final CompositeDisposable compositeDisposable;
    private Repository repository;

    public WeatherByLocationViewModel() {
        weatherByPlaceModels = new MutableLiveData<>();
        weatherByLocationModel = new MutableLiveData<>();
        compositeDisposable = new CompositeDisposable();
        repository = new Repository();
    }

    public MutableLiveData<WeatherByPlaceNameModel> getWeatherByPlaceModel(String place, String key ) {
        compositeDisposable.add(repository.getWeatherByPlace( place, key)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(weatherModel -> {
                    weatherByPlaceModels.setValue(weatherModel);
                }));
        return weatherByPlaceModels;
    }

    public MutableLiveData<WeatherByLocationModel> getWeatherByLocationModel(double lat, double lon, String excludes, String key ) {
        compositeDisposable.add(repository.getWeatherByLocation( lat, lon, excludes, key)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(weatherModel -> {
                    weatherByLocationModel.setValue(weatherModel);
                }));
        return weatherByLocationModel;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }
}
