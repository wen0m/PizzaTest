package ru.example.weathertestapp.presentation.presenters;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;
import ru.example.weathertestapp.domain.model.WeatherForecast;
import ru.example.weathertestapp.domain.usecase.IWeatherForecastUseCase;
import ru.example.weathertestapp.presentation.enums.RefreshingType;
import ru.example.weathertestapp.presentation.views.IWeatherForecastView;
import ru.example.weathertestapp.utils.ConnectionDetector;

@InjectViewState
public class WeatherForecastPresenter extends MvpPresenter<IWeatherForecastView> implements IWeatherForecastPresenter {

    public final static int COUNT_DAYS_THREE = 3;
    public final static int COUNT_DAYS_SEVEN = 7;

    private IWeatherForecastUseCase mUseCase;
    private ConnectionDetector mConnectionDetector;
    private int mCityId;
    private int mCountDays;

    @Inject
    public WeatherForecastPresenter(IWeatherForecastUseCase useCase, ConnectionDetector connectionDetector) {
        mUseCase = useCase;
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
    public void loadWeatherForecast(int cityId, int countDays) {
        loadWeatherForecast(cityId, countDays, RefreshingType.STANDARD);
    }

    @Override
    public void loadWeatherForecast(int cityId, int countDays, RefreshingType refreshingType) {
        mUseCase.loadWeatherForecast(cityId, countDays)
                .doOnSubscribe(disposable -> showProgress(refreshingType))
                .doAfterTerminate(() -> hideProgress(refreshingType))
                .subscribe(new Consumer<List<WeatherForecast>>() {
                    @Override
                    public void accept(List<WeatherForecast> weatherForecasts) {
                        getViewState().showWeatherForecasts(weatherForecasts);
                        if (!checkNetworkAvailableAndConnected()) {
                            getViewState().showNotConnection();
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        getViewState()
                                .showErrorWeatherForecasts();
                    }
                });
    }

    @Override
    public void updateCountDaysAndItsImage(boolean isClickingImage) {
        if (isClickingImage) {
            if (mCountDays == COUNT_DAYS_THREE) {
                mCountDays = COUNT_DAYS_SEVEN;
                getViewState().setCountDays(mCountDays);
                getViewState().showCountDaysImageThree();
            } else {
                mCountDays = COUNT_DAYS_THREE;
                getViewState().setCountDays(mCountDays);
                getViewState().showCountDaysImageSeven();
            }
        } else {
            getViewState().setCountDays(mCountDays);
            if (mCountDays == COUNT_DAYS_THREE) {
                getViewState().showCountDaysImageSeven();
            } else {
                getViewState().showCountDaysImageThree();
            }
        }
    }

    public int getCityId() {
        return mCityId;
    }

    public void setCityId(int cityId) {
        mCityId = cityId;
    }

    public int getCountDays() {
        return mCountDays;
    }

    public void setCountDays(int countDays) {
        mCountDays = countDays;
    }

    private void showProgress(RefreshingType refreshingType) {
        switch (refreshingType) {
            case STANDARD:
                getViewState().showLoading();
                break;
            case UPDATE_BUTTON:
                getViewState().showButtonRefreshing();
                break;
        }
    }

    private void hideProgress(RefreshingType refreshingType) {
        switch (refreshingType) {
            case STANDARD:
                getViewState().hideLoading();
                break;
            case UPDATE_BUTTON:
                getViewState().hideButtonRefreshing();
                break;
        }
    }

    private boolean checkNetworkAvailableAndConnected() {
        return mConnectionDetector.isNetworkAvailableAndConnected();
    }
}