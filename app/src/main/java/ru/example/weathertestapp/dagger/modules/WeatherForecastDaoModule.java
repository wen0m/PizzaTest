package ru.example.weathertestapp.dagger.modules;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import ru.example.weathertestapp.dagger.scope.ForWeatherForecast;
import ru.example.weathertestapp.data.db.IWeatherForecastDao;
import ru.example.weathertestapp.data.db.WeatherForecastDao;

@Module
public class WeatherForecastDaoModule {

    @Provides
    @ForWeatherForecast
    public IWeatherForecastDao provideWeatherForecastDao(Context context) {
        return WeatherForecastDao.getInstance(context.getApplicationContext());
    }
}
