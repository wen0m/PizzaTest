package ru.example.weathertestapp.dagger.components;

import dagger.Subcomponent;
import ru.example.weathertestapp.dagger.modules.CitiesUseCaseModule;
import ru.example.weathertestapp.dagger.scope.ForCities;
import ru.example.weathertestapp.presentation.activities.SplashActivity;
import ru.example.weathertestapp.presentation.fragments.AddCityFragment;

@ForCities
@Subcomponent(modules = {CitiesUseCaseModule.class})
public interface CitiesComponent {

    void inject(AddCityFragment fragment);

    void inject(SplashActivity activity);
}
