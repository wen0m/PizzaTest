package ru.example.weathertestapp.presentation.activities;

import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import ru.example.weathertestapp.R;
import ru.example.weathertestapp.domain.model.City;
import ru.example.weathertestapp.domain.model.WeatherCurrent;
import ru.example.weathertestapp.presentation.App;
import ru.example.weathertestapp.presentation.adapters.WeatherCurrentCitiesAdapter;
import ru.example.weathertestapp.presentation.enums.RefreshingType;
import ru.example.weathertestapp.presentation.fragments.AddCityFragment;
import ru.example.weathertestapp.presentation.listeners.RecyclerItemClickListener;
import ru.example.weathertestapp.presentation.preferences.AppPreference;
import ru.example.weathertestapp.presentation.presenters.WeatherCurrentsPresenter;
import ru.example.weathertestapp.presentation.views.IWeatherCurrentsView;

public class CitiesListActivity extends BaseActivity implements AddCityFragment.OnAddCityFragmentInteractionListener, IWeatherCurrentsView {

    @Inject
    @InjectPresenter
    WeatherCurrentsPresenter mWeatherCurrentsPresenter;

    private RecyclerView mWeatherCurrentCitiesRecyclerView;
    private WeatherCurrentCitiesAdapter mWeatherCurrentCitiesAdapter;
    private FloatingActionButton mAddCityFloatingActionButton;
    // TODO Заменить на ProgressBar
    private ProgressDialog mProgressDialog;

    private ArrayList<Integer> mCityIds;
    private List<WeatherCurrent> mWeatherCurrents = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        App.getInstance().getAppComponent().plusWeatherCurrentsComponent().inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_list);

        findComponents();

        initListeners();

        initToolbar();

        initDrawer(toolbar);

        initAdapter();

        initComponents();

        mCityIds = AppPreference.getCityIds(this);

        mProgressDialog = new ProgressDialog(this);

        if (savedInstanceState == null) {
            mWeatherCurrentsPresenter.loadWeather(mCityIds);
        }
    }

    @ProvidePresenter
    WeatherCurrentsPresenter provideWeatherCurrentsPresenter() {
        return mWeatherCurrentsPresenter;
    }

    protected void findComponents() {
        super.findComponents();
        mWeatherCurrentCitiesRecyclerView = (RecyclerView) findViewById(R.id.rv_city_list);
        mAddCityFloatingActionButton = (FloatingActionButton) findViewById(R.id.fab_add_city);
    }

    protected void initListeners() {
        mWeatherCurrentCitiesRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, mWeatherCurrentCitiesRecyclerView, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                // TODO заменить на intent.putExtra()
                AppPreference.saveCurrentCityId(CitiesListActivity.this, mWeatherCurrents.get(position).getCityId());
                AppPreference.saveCityNameAndCountryCode(CitiesListActivity.this, mWeatherCurrents.get(position).getCityName(), mWeatherCurrents.get(position).getSys().getCountryCode());
                Intent intent = new Intent(CitiesListActivity.this, WeatherCurrentDetailsActivity.class);
                startActivity(intent);
            }
        }));

        mAddCityFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initAddCityFragment();
            }
        });
    }

    private void initAddCityFragment() {
        final FragmentManager fragmentManager = getFragmentManager();
        AddCityFragment.newInstance().show(fragmentManager, "AddCityFragmentDialog");
    }

    private void initAdapter() {
        mWeatherCurrentCitiesAdapter = new WeatherCurrentCitiesAdapter(this, mWeatherCurrents);
    }

    private void initComponents() {
        mWeatherCurrentCitiesRecyclerView.setAdapter(mWeatherCurrentCitiesAdapter);
        mWeatherCurrentCitiesRecyclerView.setHasFixedSize(true);
        mWeatherCurrentCitiesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.main_menu_refresh:
                if (mCityIds != null && !mCityIds.isEmpty()) {
                    mWeatherCurrentsPresenter.loadWeather(mCityIds, RefreshingType.UPDATE_BUTTON);
                }
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onAddCityFragmentInteraction(City city) {
        if (city != null) {
            AppPreference.addCityId(this, city.getId());
            mCityIds.add(city.getId());
            mWeatherCurrentsPresenter.loadWeather(mCityIds);
        }
    }

    @Override
    public void showLoading() {
        if (!mProgressDialog.isShowing()) {
            mProgressDialog.show();
        }
    }

    @Override
    public void hideLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void showButtonRefreshing() {
        setUpdateButtonState(true);
    }

    @Override
    public void hideButtonRefreshing() {
        setUpdateButtonState(false);
    }

    @Override
    public void showWeatherCurrents(List<WeatherCurrent> weatherCurrents) {
        mWeatherCurrents = weatherCurrents;
        mWeatherCurrentCitiesAdapter.refreshList(mWeatherCurrents);
        // TODO удалить
        Toast.makeText(this,
                "Загрузка погод успешно завершена",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showErrorWeatherCurrents() {
        Toast.makeText(this,
                R.string.error_weather_currents,
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showNotConnection() {
        Toast.makeText(this,
                R.string.connection_not_found,
                Toast.LENGTH_SHORT).show();
    }
}
