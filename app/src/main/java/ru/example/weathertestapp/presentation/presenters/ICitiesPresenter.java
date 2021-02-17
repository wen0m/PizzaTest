package ru.example.weathertestapp.presentation.presenters;

import ru.example.weathertestapp.domain.model.City;


public interface ICitiesPresenter {

    void initPublishSubjectForFindCities();

    void getCitiesByToWatch(boolean isToWatch);

    void processEnteredCityName(CharSequence cityNameFirstLetters);

    void processClearCity();

    void findCities(String cityNameFirstLetters);

    void processSelectedCity(City city);

    void loadCityToWatch(City city);
}