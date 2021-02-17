package ru.example.weathertestapp.presentation.presenters;

import java.util.ArrayList;

import ru.example.weathertestapp.presentation.enums.RefreshingType;

public interface IWeatherCurrentsPresenter {

    void loadWeather(ArrayList<Integer> cityIds);

    void loadWeather(ArrayList<Integer> cityIds, RefreshingType refreshingType);
}
