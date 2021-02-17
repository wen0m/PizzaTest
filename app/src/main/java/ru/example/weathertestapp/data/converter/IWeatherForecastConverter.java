package ru.example.weathertestapp.data.converter;

import android.database.Cursor;

import java.util.List;

import ru.example.weathertestapp.domain.model.WeatherForecast;

public interface IWeatherForecastConverter {

    List<WeatherForecast> convertAll(Cursor cursor);

    WeatherForecast convert(Cursor cursor);
}