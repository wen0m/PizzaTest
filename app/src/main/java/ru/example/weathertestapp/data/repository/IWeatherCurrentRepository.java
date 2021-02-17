package ru.example.weathertestapp.data.repository;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import ru.example.weathertestapp.domain.model.WeatherCurrent;

public interface IWeatherCurrentRepository {

    Observable<List<WeatherCurrent>> getWeatherCurrents(ArrayList<Integer> cityIds);

    Observable<WeatherCurrent> getWeatherCurrent(int cityId);

}
