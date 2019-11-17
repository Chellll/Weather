package com.example.weather.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {

    @GET("weather")
    Call<CurrentWeather> getCurrentWeather(
           // @Query("lat") double latitude,
           // @Query("lon") double longitude,
           // @Query("appid") String apikey
           @Query("q") String city,
           @Query("appid") String apikey
    );

}
