package ru.example.weathertestapp.data.cache;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.functions.Function;
import ru.example.weathertestapp.data.db.IWeatherCurrentDao;
import ru.example.weathertestapp.data.model.WeatherCurrentModel;
import ru.example.weathertestapp.domain.model.WeatherCurrent;

public class WeatherCurrentsCacheTransformer implements ObservableTransformer<List<WeatherCurrentModel>, List<WeatherCurrent>> {

    private IWeatherCurrentDao mWeatherCurrentDao;

    private final Function<List<WeatherCurrentModel>, Observable<List<WeatherCurrent>>> mSaveFunc = weatherCurrentModels -> {
        mWeatherCurrentDao.insertOrUpdate(weatherCurrentModels);
        List<WeatherCurrent> weatherCurrents = mWeatherCurrentDao.getAll();
        return Observable.just(weatherCurrents);
    };

    private final Function<Throwable, Observable<List<WeatherCurrent>>> mCacheErrorHandler = throwable -> {
        List<WeatherCurrent> weatherCurrents = mWeatherCurrentDao.getAll();
        return Observable.just(weatherCurrents);
    };

    public WeatherCurrentsCacheTransformer(IWeatherCurrentDao weatherCurrentDao) {
        mWeatherCurrentDao = weatherCurrentDao;
    }

    @Override
    public ObservableSource<List<WeatherCurrent>> apply(Observable<List<WeatherCurrentModel>> weatherCurrentsObservable) {
        return weatherCurrentsObservable
                .flatMap(mSaveFunc)
                .onErrorResumeNext(mCacheErrorHandler);
    }
}
