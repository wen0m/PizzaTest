package ru.example.weathertestapp.data.network;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.example.weathertestapp.data.model.WeatherCurrentListModel;
import ru.example.weathertestapp.data.model.WeatherCurrentModel;
import ru.example.weathertestapp.data.model.WeatherForecastModel;

public interface OwmService {

    String API_KEY = "9162388bd4e78a8f4b5748cc11597c24";
    String BASE_URL = "http://api.openweathermap.org/";

    // TODO вынести в настройки units,lang
    @GET("/data/2.5/group")
    Observable<WeatherCurrentListModel> getWeatherCurrent(@Query("id") String cityIds, @Query("units") String units, @Query("lang") String lang, @Query("appid") String apiKey);

    @GET("/data/2.5/weather")
    Observable<WeatherCurrentModel> getWeatherCurrent(@Query("id") int cityId, @Query("units") String units, @Query("lang") String lang, @Query("appid") String apiKey);

    @GET("/data/2.5/forecast/daily")
    Observable<WeatherForecastModel> getWeatherForecasts(@Query("id") int cityId, @Query("cnt") int count, @Query("units") String units, @Query("lang") String lang, @Query("appid") String apiKey);
}
