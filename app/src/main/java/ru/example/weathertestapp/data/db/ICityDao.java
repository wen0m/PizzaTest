package ru.example.weathertestapp.data.db;

import java.util.List;

import ru.example.weathertestapp.data.listeners.IDbTablesCreatedListener;
import ru.example.weathertestapp.domain.model.City;

public interface ICityDao extends IDbTablesCreatedListener {

    List<City> getCitiesByToWatch(boolean isToWatch);

    List<City> findCities(String cityNameFirstLetters);

    boolean updateCityWatched(City city);

}
