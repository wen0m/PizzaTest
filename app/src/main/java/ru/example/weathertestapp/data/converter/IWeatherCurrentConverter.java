package ru.example.weathertestapp.data.converter;

import android.database.Cursor;

import java.util.List;

import ru.example.weathertestapp.domain.model.WeatherCurrent;

public interface IWeatherCurrentConverter {

    List<WeatherCurrent> convertAll(Cursor cursor);

    WeatherCurrent convert(Cursor cursor);
}
