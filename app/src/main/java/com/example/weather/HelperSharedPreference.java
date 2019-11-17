package com.example.weather;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.weather.api.CurrentWeather;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class HelperSharedPreference {

    public static final String SHARED_PREF_NAME = "SHARED_PREF_NAME";
    public static final String CURRENT_WEATHERS_KEY = "CURRENT_WEATHERS_KEY";
    public static final Type CURRENT_WEATHERS_TYPE = new TypeToken<List<CurrentWeather>>(){}.getType();

    private SharedPreferences mSharedPreferences;

    public HelperSharedPreference(Context context){
        mSharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
    }

    private Gson mGson = new Gson();

    public List<CurrentWeather> getCurrentWeathers(){
        List<CurrentWeather> currentWeathers = mGson.fromJson(mSharedPreferences.getString(CURRENT_WEATHERS_KEY, ""), CURRENT_WEATHERS_TYPE);
        return currentWeathers == null ? new ArrayList<CurrentWeather>() : currentWeathers;
    }

    public boolean addCurrentWeather(CurrentWeather currentWeather){
        List<CurrentWeather> currentWeathers = getCurrentWeathers();

        for(CurrentWeather e : currentWeathers){
            if(e.getCityName().equalsIgnoreCase(currentWeather.getCityName())){
                return false;
            }
        }
        currentWeathers.add(currentWeather);
        mSharedPreferences.edit().putString(CURRENT_WEATHERS_KEY, mGson.toJson(currentWeathers, CURRENT_WEATHERS_TYPE)).apply();
        return true;
    }

    public boolean deleteCurrentWeather(String currentWeatherCityName){

        List<CurrentWeather> currentWeathers = getCurrentWeathers();

        for(CurrentWeather e : currentWeathers){
            if(e.getCityName().equalsIgnoreCase(currentWeatherCityName)){
                currentWeathers.remove(e);
                return true;
            }
        }
        return false;
    }


}
