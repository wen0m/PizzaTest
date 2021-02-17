package ru.example.weathertestapp.dagger.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.example.weathertestapp.data.repository.IWeatherCurrentRepository;
import ru.example.weathertestapp.domain.usecase.IWeatherCurrentUseCase;
import ru.example.weathertestapp.domain.usecase.WeatherCurrentUseCase;

@Module(includes = {WeatherCurrentRepositoryModule.class})
public class WeatherCurrentUseCaseModule {

    @Provides
    @Singleton
    public IWeatherCurrentUseCase provideWeatherCurrentUseCase(IWeatherCurrentRepository weatherCurrentRepository) {
        return new WeatherCurrentUseCase(weatherCurrentRepository);
    }
}
