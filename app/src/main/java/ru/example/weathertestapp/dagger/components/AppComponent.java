package ru.example.weathertestapp.dagger.components;

import javax.inject.Singleton;

import dagger.Component;
import ru.example.weathertestapp.dagger.modules.AppModule;
import ru.example.weathertestapp.dagger.modules.ConnectionDetectorModule;
import ru.example.weathertestapp.dagger.modules.WeatherCurrentUseCaseModule;

@Singleton
@Component(
        modules = {
                AppModule.class,
                ConnectionDetectorModule.class,
                WeatherCurrentUseCaseModule.class
        }
)
public interface AppComponent {

    CitiesComponent plusCitiesComponent();

    WeatherCurrentsComponent plusWeatherCurrentsComponent();

    WeatherCurrentComponent plusWeatherCurrentComponent();

    WeatherForecastComponent plusWeatherForecastComponent();
}