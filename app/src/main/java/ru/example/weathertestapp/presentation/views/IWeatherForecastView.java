package ru.example.weathertestapp.presentation.views;

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;

import ru.example.weathertestapp.domain.model.WeatherForecast;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface IWeatherForecastView extends ILoadingView, IButtonRefreshingView, INotConnectionView {

    @StateStrategyType(SingleStateStrategy.class)
    void showWeatherForecasts(List<WeatherForecast> weatherForecasts);

    @StateStrategyType(SkipStrategy.class)
    void showErrorWeatherForecasts();

    void setCountDays(int countDays);

    void showCountDaysImageThree();

    void showCountDaysImageSeven();
}