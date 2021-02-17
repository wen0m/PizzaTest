package ru.example.weathertestapp.data.repository;

import java.util.List;

import io.reactivex.Observable;
import ru.example.weathertestapp.domain.model.WeatherForecast;

public interface IWeatherForecastRepository {

    Observable<List<WeatherForecast>> getWeatherForecasts(int cityId, int countDays);
}