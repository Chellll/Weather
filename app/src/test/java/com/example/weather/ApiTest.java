package com.example.weather;

import com.example.weather.api.Api;
import com.example.weather.api.ApiFactory;
import com.example.weather.api.Constants;
import com.example.weather.api.CurrentWeather;
import com.example.weather.api.ForecastMain;
import com.example.weather.api.ForecastWind;

import junit.framework.Assert;

import org.junit.Test;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class ApiTest {

    private final Api api = ApiFactory.createApi();

    @Test
    public void testCurrentWeather() throws IOException {
        Call<CurrentWeather> call = api.getCurrentWeather(
                "Moscow",
                Constants.API_KEY);

        Response<CurrentWeather> response = call.execute();

        // Проверим, что запрос завершился с кодом 2XX
        Assert.assertTrue(response.isSuccessful());

        CurrentWeather body = response.body();

        // Проверим, что тело запроса распарсилось нормально
        Assert.assertNotNull(body);

        String cityName = body.getCityName();

        // Проверим, что есть имя города
        Assert.assertNotNull(cityName);
        Assert.assertTrue(cityName.length() > 0);

        // Проверим, что есть основные данные о погоде
        ForecastMain forecastMain = body.getMain();

        Assert.assertNotNull(forecastMain);

        // На самом деле, тесты могут фейлиться, например, если температура будет
        // действительно равна 0. Но для примера сойдёт
        Assert.assertNotSame(0, forecastMain.getHumidity());
        Assert.assertNotSame(0, forecastMain.getTemp());
        Assert.assertNotSame(0, forecastMain.getMinTemp());
        Assert.assertNotSame(0, forecastMain.getMaxTemp());

        // Проверим, что есть данные о ветре
        ForecastWind forecastWind = body.getWind();

        Assert.assertNotNull(forecastWind);

        Assert.assertNotSame(0, forecastWind.getDegree());
        Assert.assertNotSame(0, forecastWind.getSpeed());
    }

}
