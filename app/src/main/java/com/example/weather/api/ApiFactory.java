package com.example.weather.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiFactory {

    public static Api createApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.API_BASE_URL) // Базовый URL
                .addConverterFactory(GsonConverterFactory.create()) // Конвертер JSON
                .build();

        return retrofit.create(Api.class);
    }

}
