package com.example.weatherapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherapp.model.weather_by_location.Daily;
import com.example.weatherapp.model.weather_by_location.WeatherByLocationModel;
import com.example.weatherapp.model.weather_by_place.WeatherByPlaceNameModel;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdaptor extends RecyclerView.Adapter<RecyclerAdaptor.holder> {

    private List<Daily> arrayList;
    private Context context;

    public RecyclerAdaptor(List<Daily> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_layout, parent, false);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int position) {
        double tempValue = arrayList.get(position).getTemp().getMax() - 273.15;
        holder.maxTemp.setText(String.format("%.0f", tempValue) + "ºc");
//        tempValue = arrayList.get(position).getTemp().getMin()- 273.15;
//        holder.minTemp.setText(String.format("%.0f", tempValue)+"ºc");
        holder.minTemp.setText(arrayList.get(position).getWeather().get(0).getMain());
        setIcon(arrayList.get(position).getWeather().get(0).getDescription(), holder);

    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private void setIcon(String description, holder holder) {
        if (description.contains("clear sky")) {
            holder.imageView.setImageResource(R.drawable.sun);
        } else if (description.contains("few clouds")) {
            holder.imageView.setImageResource(R.drawable.cloud);
        } else if (description.contains("scattered clouds")) {
            holder.imageView.setImageResource(R.drawable.cloud);
        } else if (description.contains("broken clouds")) {
            holder.imageView.setImageResource(R.drawable.cloud);
        } else if (description.contains("shower rain")) {
            holder.imageView.setImageResource(R.drawable.rain);
        } else if (description.contains("rain")) {
            holder.imageView.setImageResource(R.drawable.rain);
        } else if (description.contains("thunderstorm")) {
            holder.imageView.setImageResource(R.drawable.thunderstorm);
        } else if (description.contains("snow")) {
            holder.imageView.setImageResource(R.drawable.snow);
        } else if (description.contains("mist")) {
            holder.imageView.setImageResource(R.drawable.mist);
        }
        holder.imageView.setColorFilter(Color.WHITE);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class holder extends RecyclerView.ViewHolder {
        private TextView maxTemp;
        private TextView minTemp;
        private ImageView imageView;

        public holder(@NonNull View itemView) {
            super(itemView);
            maxTemp = itemView.findViewById(R.id.textView);
            minTemp = itemView.findViewById(R.id.textView2);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
