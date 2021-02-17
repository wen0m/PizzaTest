package ru.example.weathertestapp.data.repository;

import java.util.List;
import java.util.Locale;

import io.reactivex.Observable;
import ru.example.weathertestapp.data.cache.WeatherForecastCacheTransformer;
import ru.example.weathertestapp.data.db.IWeatherForecastDao;
import ru.example.weathertestapp.data.network.OwmService;
import ru.example.weathertestapp.domain.model.WeatherForecast;
import ru.example.weathertestapp.presentation.App;

public class WeatherForecastRepository implements IWeatherForecastRepository {

    private IWeatherForecastDao mWeatherForecastDao;

    public WeatherForecastRepository(IWeatherForecastDao weatherForecastDao) {
        mWeatherForecastDao = weatherForecastDao;
    }

    @Override
    public Observable<List<WeatherForecast>> getWeatherForecasts(int cityId, int countDays) {
        return App.getOwmService()
                .getWeatherForecasts(cityId, countDays, "metric", Locale.getDefault().getLanguage(), OwmService.API_KEY)
                .compose(new WeatherForecastCacheTransformer(mWeatherForecastDao, cityId, countDays));

    }
}