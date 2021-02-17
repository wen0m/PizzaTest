package ru.example.weathertestapp.presentation.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ru.example.weathertestapp.R;
import ru.example.weathertestapp.domain.model.WeatherCurrent;
import ru.example.weathertestapp.presentation.adapters.holders.CityViewHolder;
import ru.example.weathertestapp.utils.ImageUtils;
import ru.example.weathertestapp.utils.UnitUtils;

public class WeatherCurrentCitiesAdapter extends RecyclerView.Adapter<CityViewHolder> {

    private Context mContext;
    private List<WeatherCurrent> mWeatherCurrents;

    public WeatherCurrentCitiesAdapter(Context context, List<WeatherCurrent> weatherCurrents) {
        mContext = context;
        mWeatherCurrents = weatherCurrents;
    }

    @Override
    public CityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.city_item, parent, false);
        return new CityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CityViewHolder holder, int position) {
        Typeface weatherFontIcon = Typeface.createFromAsset(mContext.getAssets(), "fonts/weathericons-regular-webfont.ttf");

        WeatherCurrent weatherCurrent = mWeatherCurrents.get(position);

        holder.tvCityName.setText(weatherCurrent.getCityName());
        holder.imgWeatherCurrentIcon.setTypeface(weatherFontIcon);
        holder.imgWeatherCurrentIcon.setText(ImageUtils.getStrIcon(mContext, weatherCurrent.getWeathers().get(0).getIcon()));
        // TODO когда добавлю "Настройки" поменять для работы и с °F
        holder.tvWeatherCurrentTemp.setText(mContext.getString(R.string.temperature_with_degree, UnitUtils.getFormatTemperature(weatherCurrent.getMain().getTemperature())));

    }

    @Override
    public int getItemCount() {
        return mWeatherCurrents.size();
    }

    public void refreshList(List<WeatherCurrent> weatherCurrents) {
        mWeatherCurrents = weatherCurrents;
        notifyDataSetChanged();
    }
}
