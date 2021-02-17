package ru.example.weathertestapp.data.db;

import java.util.List;

import ru.example.weathertestapp.data.model.WeatherCurrentModel;
import ru.example.weathertestapp.domain.model.WeatherCurrent;

public interface IWeatherCurrentDao extends ICommonDAO<WeatherCurrent> {

    void insertOrUpdate(WeatherCurrentModel weatherCurrentModel);

    void insertOrUpdate(List<WeatherCurrentModel> weatherCurrentModels);

    WeatherCurrent getWeatherCurrentByCityId(int cityId);
}
