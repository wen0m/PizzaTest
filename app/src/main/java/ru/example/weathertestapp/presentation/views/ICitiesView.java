package ru.example.weathertestapp.presentation.views;

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;

import ru.example.weathertestapp.domain.model.City;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface ICitiesView extends ILoadingView {

    void showCities(List<City> cities);

    void updateCity(City city);

    void showCityName(String cityName);

    @StateStrategyType(SkipStrategy.class)
    void processAddedCity(City city);

    @StateStrategyType(SkipStrategy.class)
    void showErrorSelectedCity();
}