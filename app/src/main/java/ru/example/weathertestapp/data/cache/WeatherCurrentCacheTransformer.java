package ru.example.weathertestapp.data.cache;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.functions.Function;
import ru.example.weathertestapp.data.db.IWeatherCurrentDao;
import ru.example.weathertestapp.data.model.WeatherCurrentModel;
import ru.example.weathertestapp.domain.model.WeatherCurrent;

public class WeatherCurrentCacheTransformer implements ObservableTransformer<WeatherCurrentModel, WeatherCurrent> {

    private IWeatherCurrentDao mWeatherCurrentDao;
    private final Function<WeatherCurrentModel, Observable<WeatherCurrent>> mSaveFunc = weatherCurrentModel -> {
        mWeatherCurrentDao.insertOrUpdate(weatherCurrentModel);
        WeatherCurrent weatherCurrent = mWeatherCurrentDao.getWeatherCurrentByCityId(weatherCurrentModel.getCityId());
        return Observable.just(weatherCurrent);
    };
    private int mCityId;
    private final Function<Throwable, Observable<WeatherCurrent>> mCacheErrorHandler = throwable -> {
        WeatherCurrent weatherCurrent = mWeatherCurrentDao.getWeatherCurrentByCityId(mCityId);
        return Observable.just(weatherCurrent);
    };

    public WeatherCurrentCacheTransformer(IWeatherCurrentDao weatherCurrentDao, int cityId) {
        mWeatherCurrentDao = weatherCurrentDao;
        mCityId = cityId;
    }

    @Override
    public ObservableSource<WeatherCurrent> apply(Observable<WeatherCurrentModel> weatherCurrentObservable) {
        return weatherCurrentObservable
                .flatMap(mSaveFunc)
                .onErrorResumeNext(mCacheErrorHandler);
    }
}
