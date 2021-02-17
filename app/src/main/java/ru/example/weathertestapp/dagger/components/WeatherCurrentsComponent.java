package ru.example.weathertestapp.dagger.components;

import dagger.Subcomponent;
import ru.example.weathertestapp.dagger.scope.ForWeatherCurrent;
import ru.example.weathertestapp.presentation.activities.CitiesListActivity;

@ForWeatherCurrent
@Subcomponent
public interface WeatherCurrentsComponent {

    void inject(CitiesListActivity activity);
}
