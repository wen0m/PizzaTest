package ru.example.weathertestapp.presentation.views;

import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import ru.example.weathertestapp.domain.model.WeatherCurrent;

@StateStrategyType(SkipStrategy.class)
public interface IWeatherCurrentView extends ISwipeRefreshingView, IButtonRefreshingView, INotConnectionView {

    @StateStrategyType(SingleStateStrategy.class)
    void showWeatherCurrent(WeatherCurrent weatherCurrent);

    void showErrorWeatherCurrent();
}
