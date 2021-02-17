package ru.example.weathertestapp.dagger.modules;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.example.weathertestapp.utils.ConnectionDetector;

@Module(includes = {AppModule.class})
public class ConnectionDetectorModule {

    @Provides
    @Singleton
    public ConnectionDetector provideConnectionDetector(Context context) {
        return ConnectionDetector.getInstance(context.getApplicationContext());
    }
}
