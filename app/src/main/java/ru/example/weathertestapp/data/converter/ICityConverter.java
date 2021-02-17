package ru.example.weathertestapp.data.converter;

import android.database.Cursor;

import java.util.List;

import ru.example.weathertestapp.domain.model.City;

public interface ICityConverter {

    List<City> convertAll(Cursor cursor);

}
