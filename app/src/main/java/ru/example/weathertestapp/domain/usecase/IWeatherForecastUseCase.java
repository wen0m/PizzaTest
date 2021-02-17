package ru.example.weathertestapp.domain.usecase;

import java.util.List;

import io.reactivex.Observable;
import ru.example.weathertestapp.domain.model.WeatherForecast;

public interface IWeatherForecastUseCase {

    Observable<List<WeatherForecast>> loadWeatherForecast(int cityId, int countDays);
}