package ru.example.weathertestapp.data.db;

import java.util.List;

import ru.example.weathertestapp.data.model.City;
import ru.example.weathertestapp.data.model.WeatherForecastModel;
import ru.example.weathertestapp.domain.model.WeatherForecast;

public interface IWeatherForecastDao {

    void insertOrUpdate(WeatherForecastModel weatherForecastModel);

    void insertOrUpdate(ru.example.weathertestapp.data.model.WeatherForecast weatherForecast, City city);

    List<WeatherForecast> getWeatherForecastsByCityId(int cityId, int countDays);
}
