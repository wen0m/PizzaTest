package ru.example.weathertestapp.presentation.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;
import ru.example.weathertestapp.domain.model.WeatherCurrent;
import ru.example.weathertestapp.domain.usecase.IWeatherCurrentUseCase;
import ru.example.weathertestapp.presentation.enums.RefreshingType;
import ru.example.weathertestapp.presentation.views.IWeatherCurrentView;
import ru.example.weathertestapp.utils.ConnectionDetector;

@InjectViewState
public class WeatherCurrentPresenter extends MvpPresenter<IWeatherCurrentView> implements IWeatherCurrentPresenter {

    private final IWeatherCurrentUseCase mWeatherCurrentUseCase;
    private final ConnectionDetector mConnectionDetector;

    @Inject
    public WeatherCurrentPresenter(IWeatherCurrentUseCase weatherCurrentUseCase, ConnectionDetector connectionDetector) {
        mWeatherCurrentUseCase = weatherCurrentUseCase;
        mConnectionDetector = connectionDetector;
    }

    /**
     * Вызывается тогда, когда к конкретному экземпляру Presenter первый раз будет привязана любая View
     */
    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
    }

    @Override
    public void loadWeather(int cityId) {
        loadWeather(cityId, RefreshingType.STANDARD);
    }

    @Override
    public void loadWeather(int cityId, RefreshingType refreshingType) {
        mWeatherCurrentUseCase.loadWeather(cityId)
                .doOnSubscribe(disposable -> showProgress(refreshingType))
                .doAfterTerminate(() -> hideProgress(refreshingType))
                .subscribe(new Consumer<WeatherCurrent>() {
                    @Override
                    public void accept(WeatherCurrent weatherCurrent) {
                        getViewState().showWeatherCurrent(weatherCurrent);
                        if (!checkNetworkAvailableAndConnected()) {
                            getViewState().showNotConnection();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        getViewState()
                                .showErrorWeatherCurrent();
                    }
                });
    }

    private void showProgress(RefreshingType refreshingType) {
        switch (refreshingType) {
            case UPDATE_BUTTON:
                getViewState().showButtonRefreshing();
                break;
            case SWIPE:
                getViewState().showSwipeRefreshing();
                break;
        }
    }

    private void hideProgress(RefreshingType refreshingType) {
        switch (refreshingType) {
            case UPDATE_BUTTON:
                getViewState().hideButtonRefreshing();
                break;
            case SWIPE:
                getViewState().hideSwipeRefreshing();
                break;
        }
    }

    private boolean checkNetworkAvailableAndConnected() {
        return mConnectionDetector.isNetworkAvailableAndConnected();
    }
}
