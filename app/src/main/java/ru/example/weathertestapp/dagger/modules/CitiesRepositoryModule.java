package ru.example.weathertestapp.dagger.modules;

import dagger.Module;
import dagger.Provides;
import ru.example.weathertestapp.dagger.scope.ForCities;
import ru.example.weathertestapp.data.db.ICityDao;
import ru.example.weathertestapp.data.repository.CitiesRepository;
import ru.example.weathertestapp.data.repository.ICitiesRepository;

@Module(includes = {CityDaoModule.class})
public class CitiesRepositoryModule {

    @Provides
    @ForCities
    public ICitiesRepository provideCitiesRepository(ICityDao cityDao) {
        return new CitiesRepository(cityDao);
    }

}
