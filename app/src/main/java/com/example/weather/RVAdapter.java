package com.example.weather;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weather.api.CurrentWeather;

import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.CurrentWeatherViewHolder> {

    public static class CurrentWeatherViewHolder extends RecyclerView.ViewHolder {

        HelperSharedPreference sharedPreference ;

        CardView cv;
        TextView tvName;
        TextView tvTemp;
        TextView tvDegree;
        TextView tvSpeed;
        Button btnDelete;

        CurrentWeatherViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            tvName = itemView.findViewById(R.id.idName);
            tvTemp = itemView.findViewById(R.id.idTemp);
            tvDegree = itemView.findViewById(R.id.idDegree);
            tvSpeed = itemView.findViewById(R.id.idSpeed);
            btnDelete = itemView.findViewById(R.id.btnDelete);


        }
    }

    List<CurrentWeather> currentWeathers;
    HelperSharedPreference helperSharedPreference;

    public RVAdapter(List<CurrentWeather> currentWeathers, HelperSharedPreference helperSharedPreference){

        this.currentWeathers = currentWeathers;
        this.helperSharedPreference = helperSharedPreference;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public CurrentWeatherViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_city, viewGroup, false);
        CurrentWeatherViewHolder pvh = new CurrentWeatherViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(final CurrentWeatherViewHolder currentWeatherViewHolder, int i) {
        currentWeatherViewHolder.tvName.setText(currentWeathers.get(i).getCityName());
        final String cityName = currentWeathers.get(i).getCityName();
        currentWeatherViewHolder.tvTemp.setText(String.valueOf(currentWeathers.get(i).getMain().getTemp()));
        currentWeatherViewHolder.tvDegree.setText(String.valueOf(currentWeathers.get(i).getWind().getDegree()));
        currentWeatherViewHolder.tvSpeed.setText(String.valueOf(currentWeathers.get(i).getWind().getSpeed()));

        currentWeatherViewHolder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helperSharedPreference.deleteCurrentWeather(cityName);
                for(CurrentWeather e : currentWeathers){
                    if(e.getCityName().equalsIgnoreCase(cityName)){
                        currentWeathers.remove(e);
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return currentWeathers.size();
    }
}