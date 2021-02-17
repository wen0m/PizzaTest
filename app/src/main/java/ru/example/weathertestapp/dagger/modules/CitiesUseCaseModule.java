package ru.example.weathertestapp.dagger.modules;

import dagger.Module;
import dagger.Provides;
import ru.example.weathertestapp.dagger.scope.ForCities;
import ru.example.weathertestapp.data.repository.ICitiesRepository;
import ru.example.weathertestapp.domain.usecase.CitiesUseCase;
import ru.example.weathertestapp.domain.usecase.ICitiesUseCase;

@Module(includes = {CitiesRepositoryModule.class})
public class CitiesUseCaseModule {

    @Provides
    @ForCities
    public ICitiesUseCase provideCitiesUseCase(ICitiesRepository citiesRepository) {
        return new CitiesUseCase(citiesRepository);
    }

}
