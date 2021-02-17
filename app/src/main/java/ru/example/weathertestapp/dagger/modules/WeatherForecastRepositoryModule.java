package ru.example.weathertestapp.dagger.modules;

import dagger.Module;
import dagger.Provides;
import ru.example.weathertestapp.dagger.scope.ForWeatherForecast;
import ru.example.weathertestapp.data.db.IWeatherForecastDao;
import ru.example.weathertestapp.data.repository.IWeatherForecastRepository;
import ru.example.weathertestapp.data.repository.WeatherForecastRepository;

@Module(includes = {WeatherForecastDaoModule.class})
public class WeatherForecastRepositoryModule {

    @Provides
    @ForWeatherForecast
    public IWeatherForecastRepository provideWeatherForecastRepository(IWeatherForecastDao weatherForecastDao) {
        return new WeatherForecastRepository(weatherForecastDao);
    }
}
