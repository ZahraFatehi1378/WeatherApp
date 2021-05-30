package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.weatherapp.model.WeatherModel;
import com.example.weatherapp.remote.APIService;
import com.example.weatherapp.remote.RetrofitClass;
import com.example.weatherapp.ui.day.Sun;
import com.example.weatherapp.ui.night.SpiralView;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.observers.DisposableObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {


    CompositeDisposable compositeDisposable;
    String url = "https://samples.openweathermap.org/data/2.5/";
    APIService request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        compositeDisposable = new CompositeDisposable();
        request = RetrofitClass.getApi(url).create(APIService.class);

        compositeDisposable.add(request.getWeatherModel("London" , "332c0626ca316418dac6748b502c05e4")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<WeatherModel>() {
                    @Override
                    public void accept(WeatherModel weatherModel) throws Throwable {
                        Toast.makeText(getApplicationContext(), "aaaaaaaaaa", Toast.LENGTH_SHORT).show();
                        System.out.println("))))))))))))))))");
                        System.out.println(weatherModel.getName());
                    }
                }));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }





//    private CompositeDisposable disposables = new CompositeDisposable();
//    private CompositeDisposable disposable;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        getSupportActionBar().hide();
//
//        ConstraintLayout bg = findViewById(R.id.bg);
//        setBG("d", bg);
//
//        disposable = new CompositeDisposable();

//        @NonNull Observable<WeatherModel> model = apiService.getWeatherModel("London", "332c0626ca316418dac6748b502c05e4")
//                .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io());
//                model.subscribe(new Observer<WeatherModel>() {
//                    @Override
//                    public void onSubscribe(@NonNull Disposable d) {
//                        disposables.add(d);
//                    }
//
//                    @Override
//                    public void onNext(@NonNull WeatherModel weatherModel) {
//                        System.out.println("####################################");
//                        System.out.println(weatherModel);
//                    }
//
//                    @Override
//                    public void onError(@NonNull Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });



//        disposable.add(apiService.getWeatherModel("London", "332c0626ca316418dac6748b502c05e4")
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(Schedulers.io())
//                .subscribe(new Consumer<WeatherModel>(){
//                    @Override
//                    public void accept(WeatherModel w) throws Throwable {
//                        System.out.println("00000000000000000000");
//                        System.out.println(w.getName());
//
//                    }
//                }));

//        apiService.getWeatherModel("London", "332c0626ca316418dac6748b502c05e4")
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeWith(new DisposableObserver<WeatherModel>() {
//                    @Override
//                    public void onNext(@NonNull WeatherModel weatherModel) {
//                        System.out.println("####################################");
//                        System.out.println(weatherModel);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        //Handle error
//                        Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onComplete() {
//                    }
//                });
 //   }

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        disposables.clear();
//    }

    private void setBG(String state, ConstraintLayout bg) {
        Sun sun = findViewById(R.id.sun);
        SpiralView spiralView2 = findViewById(R.id.spiralView2);

        if (state.equals("d")) {
            bg.setBackground(getResources().getDrawable(R.drawable.day));
            sun.setVisibility(View.VISIBLE);
            spiralView2.setVisibility(View.GONE);
        } else {
            sun.setVisibility(View.GONE);
            spiralView2.setVisibility(View.VISIBLE);
            bg.setBackground(getResources().getDrawable(R.drawable.night));
            spiralView2.setSpiralRadius(450f);
            spiralView2.setSpiralRotation(90f);
            spiralView2.setSpacesBetweenCircles(3);
            //  spiralView2.setCoils(2f);
        }
    }


}