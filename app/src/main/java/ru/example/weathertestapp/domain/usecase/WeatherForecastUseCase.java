package ru.example.weathertestapp.domain.usecase;

import java.util.List;

import io.reactivex.Observable;
import ru.example.weathertestapp.data.repository.IWeatherForecastRepository;
import ru.example.weathertestapp.domain.model.WeatherForecast;
import ru.example.weathertestapp.utils.RxUtils;

public class WeatherForecastUseCase implements IWeatherForecastUseCase {

    private final IWeatherForecastRepository mRepository;

    public WeatherForecastUseCase(IWeatherForecastRepository repository) {
        mRepository = repository;
    }

    @Override
    public Observable<List<WeatherForecast>> loadWeatherForecast(int cityId, int countDays) {
        return mRepository.getWeatherForecasts(cityId, countDays)
                .compose(RxUtils.async());
    }
}