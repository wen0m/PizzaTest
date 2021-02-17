package ru.example.weathertestapp.presentation.views;

import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;

import ru.example.weathertestapp.domain.model.WeatherCurrent;

@StateStrategyType(SkipStrategy.class)
public interface IWeatherCurrentsView extends ILoadingView, IButtonRefreshingView, INotConnectionView {

    @StateStrategyType(SingleStateStrategy.class)
    void showWeatherCurrents(List<WeatherCurrent> weatherCurrents);

    void showErrorWeatherCurrents();
}
