package ru.example.weathertestapp.data.files;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import ru.example.weathertestapp.domain.model.City;
import ru.example.weathertestapp.domain.model.Coordinate;

public class CityFileReader {
    // TODO выбрать и убрать лишние города, а то файл очень большой
    // TODO отрефакторить
    public List<City> readCityListFromFile(InputStream is) throws IOException {
        List<City> cities = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line = br.readLine();
        while ((line = br.readLine()) != null) {
            String[] fields = line.split("\t");
            int id = Integer.parseInt(fields[0]);
            String name = fields[1];
            String countryCode = fields[2];
            float lon = Float.valueOf(fields[3]);
            float lat = Float.valueOf(fields[4]);
            int isWatched = Integer.parseInt(fields[5]);
            cities.add(new City(id, name, Coordinate.builder().longitude(lon).latitude(lat).build(), countryCode, isWatched));
        }
        br.close();
        return cities;
    }
}
