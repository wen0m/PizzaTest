package ru.example.weathertestapp.presentation.adapters.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import ru.example.weathertestapp.R;

public class CityViewHolder extends RecyclerView.ViewHolder {

    public final TextView tvCityName;
    public final TextView imgWeatherCurrentIcon;
    public final TextView tvWeatherCurrentTemp;

    public CityViewHolder(View itemView) {
        super(itemView);
        tvCityName = (TextView) itemView.findViewById(R.id.tv_city_name);
        imgWeatherCurrentIcon = (TextView) itemView.findViewById(R.id.ic_weather);
        tvWeatherCurrentTemp = (TextView) itemView.findViewById(R.id.tv_weather_current_temperature);
    }
}
