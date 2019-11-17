package com.example.weather;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.weather.api.CurrentWeather;

import java.util.ArrayList;
import java.util.List;

public class FirstActivity extends AppCompatActivity {

    HelperSharedPreference helperSharedPreference;

    private List<CurrentWeather> currentWeathers;
    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_first);

        helperSharedPreference = new HelperSharedPreference(this);

        rv=(RecyclerView)findViewById(R.id.rv);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);



        initializeData();
        initializeAdapter();
    }

    private void initializeData(){
        currentWeathers = helperSharedPreference.getCurrentWeathers();

    }

    private void initializeAdapter(){
        RVAdapter adapter = new RVAdapter(currentWeathers);
        Toast.makeText(FirstActivity.this, String.valueOf(adapter.getItemCount()), Toast.LENGTH_SHORT).show();
        Toast.makeText(FirstActivity.this, String.valueOf(currentWeathers.size()), Toast.LENGTH_SHORT).show();
        rv.setAdapter(adapter);
    }

}
