package com.example.weather;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.weather.api.CurrentWeather;

import java.util.ArrayList;
import java.util.List;

public class FirstActivity extends AppCompatActivity {

    HelperSharedPreference helperSharedPreference;

    private List<CurrentWeather> currentWeathers;
    private RecyclerView rv;

    private Button addCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_first);

        helperSharedPreference = new HelperSharedPreference(this);

        rv=(RecyclerView)findViewById(R.id.rv);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);

        addCity = findViewById(R.id.addCity);
        addCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        initializeData();
        initializeAdapter();
    }

    private void initializeData(){
        currentWeathers = helperSharedPreference.getCurrentWeathers();

    }

    private void initializeAdapter(){
        RVAdapter adapter = new RVAdapter(currentWeathers, helperSharedPreference);
        rv.setAdapter(adapter);
    }

}
