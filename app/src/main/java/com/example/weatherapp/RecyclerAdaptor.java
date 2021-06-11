package com.example.weatherapp;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherapp.model.weather_by_place.WeatherByPlaceNameModel;

import java.util.ArrayList;

public class RecyclerAdaptor extends RecyclerView.Adapter<RecyclerAdaptor.holder> {

    private ArrayList<WeatherByPlaceNameModel> weatherModels;
    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class holder extends RecyclerView.ViewHolder{
        public holder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
