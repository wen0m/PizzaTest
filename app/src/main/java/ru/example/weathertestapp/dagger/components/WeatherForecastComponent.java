package ru.example.weathertestapp.dagger.components;

import dagger.Subcomponent;
import ru.example.weathertestapp.dagger.modules.WeatherForecastUseCaseModule;
import ru.example.weathertestapp.dagger.scope.ForWeatherForecast;
import ru.example.weathertestapp.presentation.activities.WeatherForecastActivity;

@ForWeatherForecast
@Subcomponent(modules = {WeatherForecastUseCaseModule.class})
public interface WeatherForecastComponent {

    void inject(WeatherForecastActivity activity);
}
