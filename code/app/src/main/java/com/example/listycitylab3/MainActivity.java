package com.example.listycitylab3;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements AddCityFragment.AddCityDialogueListener {

    @Override
    public void addCity(City city) {
        cityAdapter.add(city);
        cityAdapter.notifyDataSetChanged();
    }

    private ArrayList<City> dataList;
    private ListView cityList;
    private CityArrayAdapter cityAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] cities = {
                "Edmonton", "Vancouver", "Toronto"
        };

        String[] provinces = {
                "AB", "BC", "ON"
        };

        dataList = new ArrayList<>();
        for (int i=0; i<cities.length; ++i) {
            dataList.add(new City(cities[i], provinces[i]));
        }
        
        cityList = findViewById(R.id.city_list);
        cityAdapter = new CityArrayAdapter(this, dataList);
        cityList.setAdapter(cityAdapter);

        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parentView, View view, int position, long id) {
                City clickedCity = dataList.get(position);

                EditCityFragment editFragment = EditCityFragment.newInstance(clickedCity, position);
                editFragment.show(getSupportFragmentManager(), "Edit City");
            }
        });

        Button button = findViewById(R.id.addCityButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AddCityFragment().show(getSupportFragmentManager(), "Add City");
            }
        });
    }
    public void updateCity(City updatedCity, int position) {
        dataList.set(position, updatedCity);
        cityAdapter.notifyDataSetChanged();
    }
}

