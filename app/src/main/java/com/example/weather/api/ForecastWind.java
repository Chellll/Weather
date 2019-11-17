package com.example.weather.api;

import com.google.gson.annotations.SerializedName;

public class ForecastWind {

    private float speed;

    @SerializedName("deg")
    private float degree;


    //скорость ветра
    public float getSpeed() {
        return speed;
    }

    //направление ветра
    public float getDegree() {
        return degree;
    }

}
