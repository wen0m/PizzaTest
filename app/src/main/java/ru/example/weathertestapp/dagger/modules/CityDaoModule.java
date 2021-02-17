package ru.example.weathertestapp.dagger.modules;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import ru.example.weathertestapp.dagger.scope.ForCities;
import ru.example.weathertestapp.data.db.CityDao;
import ru.example.weathertestapp.data.db.ICityDao;

@Module
public class CityDaoModule {

    @Provides
    @ForCities
    public ICityDao provideCityDao(Context context) {
        return CityDao.getInstance(context.getApplicationContext());
    }
}
