package ru.example.weathertestapp.presentation.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ru.example.weathertestapp.R;
import ru.example.weathertestapp.domain.model.Temperature;
import ru.example.weathertestapp.domain.model.Weather;
import ru.example.weathertestapp.domain.model.WeatherForecast;
import ru.example.weathertestapp.presentation.adapters.holders.WeatherForecastViewHolder;
import ru.example.weathertestapp.utils.ImageUtils;
import ru.example.weathertestapp.utils.StringUtils;
import ru.example.weathertestapp.utils.UnitUtils;

public class WeatherForecastAdapter extends RecyclerView.Adapter<WeatherForecastViewHolder> {

    private Context mContext;
    private List<WeatherForecast> mWeatherForecasts;

    public WeatherForecastAdapter(Context context, List<WeatherForecast> weatherForecasts) {
        mContext = context;
        mWeatherForecasts = weatherForecasts;
    }

    @Override
    public WeatherForecastViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.weather_forecast_item, parent, false);
        return new WeatherForecastViewHolder(view);
    }

    @Override
    public void onBindViewHolder(WeatherForecastViewHolder holder, int position) {
        Typeface weatherFontIcon = Typeface.createFromAsset(mContext.getAssets(), "fonts/weathericons-regular-webfont.ttf");

        WeatherForecast weatherForecast = mWeatherForecasts.get(position);

        holder.tvDateTime.setText(UnitUtils.getFormatDateTimeEEEddMMMM(weatherForecast.getDateTime()));

        Weather weather = weatherForecast.getWeather().get(0);
        Temperature temperature = weatherForecast.getTemperature();

        holder.tvDescription.setText(StringUtils.firstUpperCase(weather.getDescription()));
        holder.tvTemperatureDay.setText(mContext.getString(R.string.label_temperature_day, UnitUtils.getFormatTemperature(temperature.getDay())));
        holder.tvTemperatureNight.setText(mContext.getString(R.string.label_temperature_night, UnitUtils.getFormatTemperature(temperature.getNight())));
        holder.tvIcon.setTypeface(weatherFontIcon);
        holder.tvIcon.setText(ImageUtils.getStrIcon(mContext, weather.getIcon()));
    }

    @Override
    public int getItemCount() {
        return mWeatherForecasts.size();
    }

    public void setWeatherForecasts(List<WeatherForecast> weatherForecasts) {
        mWeatherForecasts = weatherForecasts;
        notifyDataSetChanged();
    }
}
