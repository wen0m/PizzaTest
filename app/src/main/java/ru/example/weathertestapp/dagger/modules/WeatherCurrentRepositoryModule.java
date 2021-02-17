package ru.example.weathertestapp.dagger.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.example.weathertestapp.data.db.IWeatherCurrentDao;
import ru.example.weathertestapp.data.repository.IWeatherCurrentRepository;
import ru.example.weathertestapp.data.repository.WeatherCurrentRepository;

@Module(includes = {WeatherCurrentDaoModule.class})
public class WeatherCurrentRepositoryModule {

    @Provides
    @Singleton
    public IWeatherCurrentRepository provideWeatherCurrentRepository(IWeatherCurrentDao weatherCurrentDao) {
        return new WeatherCurrentRepository(weatherCurrentDao);
    }
}
