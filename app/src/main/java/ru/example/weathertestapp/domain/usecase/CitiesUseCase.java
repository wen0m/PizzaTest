package ru.example.weathertestapp.domain.usecase;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import ru.example.weathertestapp.data.repository.ICitiesRepository;
import ru.example.weathertestapp.domain.model.City;
import ru.example.weathertestapp.utils.RxUtils;

public class CitiesUseCase implements ICitiesUseCase {

    private final ICitiesRepository mRepository;

    private PublishSubject<String> subject = PublishSubject.create();

    public CitiesUseCase(ICitiesRepository repository) {
        mRepository = repository;
    }

    @Override
    public Observable<List<City>> initPublishSubjectForFindCities(int cityNameLettersMinForSearch) {
        return subject.debounce(500, TimeUnit.MILLISECONDS)
                .distinctUntilChanged()
                .map(String::toLowerCase)
                .switchMap(cityNameFirstLetters -> mRepository.findCities(cityNameFirstLetters))
                .compose(RxUtils.async());
    }

    @Override
    public Observable<List<City>> getCitiesByToWatch(boolean isToWatch) {
        return mRepository.getCitiesByToWatch(isToWatch)
                .compose(RxUtils.async());
    }

    @Override
    public void findCities(String cityNameFirstLetters) {
        subject.onNext(cityNameFirstLetters);
    }

    @Override
    public Observable<Boolean> loadCityToWatch(City city) {
        return mRepository.loadCityToWatch(city)
                .compose(RxUtils.async());
    }
}
