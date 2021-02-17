
package ru.example.weathertestapp.domain.model;

public class Sys {

    private String mCountryCode;
    private Integer mSunrise;
    private Integer mSunset;

    public Sys(String countryCode, Integer sunrise, Integer sunset) {
        mCountryCode = countryCode;
        mSunrise = sunrise;
        mSunset = sunset;
    }

    public String getCountryCode() {
        return mCountryCode;
    }

    public void setCountryCode(String countryCode) {
        mCountryCode = countryCode;
    }

    public Integer getSunrise() {
        return mSunrise;
    }

    public void setSunrise(Integer sunrise) {
        mSunrise = sunrise;
    }

    public Integer getSunset() {
        return mSunset;
    }

    public void setSunset(Integer sunset) {
        mSunset = sunset;
    }

}
