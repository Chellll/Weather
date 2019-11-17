package com.example.weather;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weather.api.Api;
import com.example.weather.api.ApiFactory;
import com.example.weather.api.Constants;
import com.example.weather.api.CurrentWeather;

public class MainActivity extends AppCompatActivity {

    HelperSharedPreference helperSharedPreference;

    private EditText etSearch;
    private Button btnSearch;
    private TextView tvAnswer;

    private Button btn;

    String city = "";

    private final Api api = ApiFactory.createApi();

    @Nullable
    private CurrentWeather currentWeather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        helperSharedPreference = new HelperSharedPreference(this);

        btn = findViewById(R.id.btnList);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FirstActivity.class);
                startActivity(intent);
            }
        });

        etSearch = findViewById(R.id.etSearch);
        btnSearch = findViewById(R.id.btnSearch);
        tvAnswer = findViewById(R.id.tvAnswer);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<CurrentWeather> call = api.getCurrentWeather(
                        etSearch.getText().toString(),
                        Constants.API_KEY
                );

                call.enqueue(new Callback<CurrentWeather>() {
                    @Override
                    public void onResponse(@NonNull Call<CurrentWeather> call, @NonNull Response<CurrentWeather> response) {
                        if (response.isSuccessful()) {
                            CurrentWeather currentWeather = response.body();
                            Toast.makeText(MainActivity.this, currentWeather.getCityName(), Toast.LENGTH_SHORT).show();
                            helperSharedPreference.addCurrentWeather(currentWeather);
                            tvAnswer.setText(city);

                        } else {
                            Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<CurrentWeather> call, @NonNull Throwable t) {
                        Toast.makeText(MainActivity.this, "FULL FAILED", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

    }
}
