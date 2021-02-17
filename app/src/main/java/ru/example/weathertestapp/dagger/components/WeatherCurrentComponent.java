package ru.example.weathertestapp.dagger.components;

import dagger.Subcomponent;
import ru.example.weathertestapp.dagger.scope.ForWeatherCurrent;
import ru.example.weathertestapp.presentation.activities.WeatherCurrentDetailsActivity;

@ForWeatherCurrent
@Subcomponent
public interface WeatherCurrentComponent {

    void inject(WeatherCurrentDetailsActivity activity);
}
