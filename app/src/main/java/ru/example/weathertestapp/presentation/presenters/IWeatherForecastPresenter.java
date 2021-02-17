package ru.example.weathertestapp.presentation.presenters;

import ru.example.weathertestapp.presentation.enums.RefreshingType;

public interface IWeatherForecastPresenter {

    void loadWeatherForecast(int cityId, int countDays);

    void loadWeatherForecast(int cityId, int countDays, RefreshingType refreshingType);

    void updateCountDaysAndItsImage(boolean isClickingImage);
}