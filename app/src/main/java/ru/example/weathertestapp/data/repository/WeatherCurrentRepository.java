package ru.example.weathertestapp.data.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import io.reactivex.Observable;
import ru.example.weathertestapp.data.cache.WeatherCurrentCacheTransformer;
import ru.example.weathertestapp.data.cache.WeatherCurrentsCacheTransformer;
import ru.example.weathertestapp.data.db.IWeatherCurrentDao;
import ru.example.weathertestapp.data.model.WeatherCurrentListModel;
import ru.example.weathertestapp.data.network.OwmService;
import ru.example.weathertestapp.domain.model.WeatherCurrent;
import ru.example.weathertestapp.presentation.App;
import ru.example.weathertestapp.utils.StringUtils;

public class WeatherCurrentRepository implements IWeatherCurrentRepository {

    private IWeatherCurrentDao mWeatherCurrentDao;

    public WeatherCurrentRepository(IWeatherCurrentDao weatherCurrentDao) {
        mWeatherCurrentDao = weatherCurrentDao;
    }

    @Override
    public Observable<List<WeatherCurrent>> getWeatherCurrents(ArrayList<Integer> cityIds) {
        return App.getOwmService()
                .getWeatherCurrent(StringUtils.joinIds(cityIds), "metric", Locale.getDefault().getLanguage(), OwmService.API_KEY)
                .map(WeatherCurrentListModel::getWeatherCurrentModelList)
                .compose(new WeatherCurrentsCacheTransformer(mWeatherCurrentDao));
    }

    @Override
    public Observable<WeatherCurrent> getWeatherCurrent(int cityId) {
        return App.getOwmService()
                .getWeatherCurrent(cityId, "metric", Locale.getDefault().getLanguage(), OwmService.API_KEY)
                .compose(new WeatherCurrentCacheTransformer(mWeatherCurrentDao, cityId));
    }
}
