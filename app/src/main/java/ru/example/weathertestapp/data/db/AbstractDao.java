package ru.example.weathertestapp.data.db;

abstract class AbstractDao {
    protected WeatherDbHelper mDbHelper;

    public AbstractDao(WeatherDbHelper dbHelper) {
        mDbHelper = dbHelper;
    }
}
