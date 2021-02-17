package ru.example.weathertestapp.dagger.modules;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.example.weathertestapp.data.db.IWeatherCurrentDao;
import ru.example.weathertestapp.data.db.WeatherCurrentDao;

@Module
public class WeatherCurrentDaoModule {

    @Provides
    @Singleton
    public IWeatherCurrentDao provideWeatherCurrentDao(Context context) {
        return WeatherCurrentDao.getInstance(context.getApplicationContext());
    }
}
