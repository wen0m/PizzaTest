package ru.example.weathertestapp.presentation.presenters;

import ru.example.weathertestapp.presentation.enums.RefreshingType;

public interface IWeatherCurrentPresenter {

    void loadWeather(int cityId);

    void loadWeather(int cityId, RefreshingType refreshingType);
}
