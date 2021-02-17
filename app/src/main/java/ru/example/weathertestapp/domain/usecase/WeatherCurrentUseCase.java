package ru.example.weathertestapp.domain.usecase;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import ru.example.weathertestapp.data.repository.IWeatherCurrentRepository;
import ru.example.weathertestapp.domain.model.WeatherCurrent;
import ru.example.weathertestapp.utils.RxUtils;

public class WeatherCurrentUseCase implements IWeatherCurrentUseCase {

    private final IWeatherCurrentRepository mRepository;

    public WeatherCurrentUseCase(IWeatherCurrentRepository repository) {
        mRepository = repository;
    }

    @Override
    public Observable<WeatherCurrent> loadWeather(int cityId) {
        return mRepository.getWeatherCurrent(cityId)
                .compose(RxUtils.async());
    }

    @Override
    public Observable<List<WeatherCurrent>> loadWeather(ArrayList<Integer> cityIds) {
        return mRepository.getWeatherCurrents(cityIds)
                .compose(RxUtils.async());
    }
}
