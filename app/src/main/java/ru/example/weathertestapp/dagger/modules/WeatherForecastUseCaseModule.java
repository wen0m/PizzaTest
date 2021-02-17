package ru.example.weathertestapp.dagger.modules;

import dagger.Module;
import dagger.Provides;
import ru.example.weathertestapp.dagger.scope.ForWeatherForecast;
import ru.example.weathertestapp.data.repository.IWeatherForecastRepository;
import ru.example.weathertestapp.domain.usecase.IWeatherForecastUseCase;
import ru.example.weathertestapp.domain.usecase.WeatherForecastUseCase;

@Module(includes = {WeatherForecastRepositoryModule.class})
public class WeatherForecastUseCaseModule {

    @Provides
    @ForWeatherForecast
    public IWeatherForecastUseCase provideWeatherForecastUseCase(IWeatherForecastRepository weatherForecastRepository) {
        return new WeatherForecastUseCase(weatherForecastRepository);
    }
}
