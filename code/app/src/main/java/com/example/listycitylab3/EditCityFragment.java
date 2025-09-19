package com.example.listycitylab3;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.DialogFragment;

public class EditCityFragment extends DialogFragment {

    public static final String city_name = "city_name";
    public static final String city_province = "city_province";
    public static final String city_pos = "city_position";

    private EditText cityNameEditText;
    private EditText cityProvinceEditText;

    private int position;
    private City city;

    public static EditCityFragment newInstance(City city, int position) {
        EditCityFragment fragment = new EditCityFragment();
        Bundle args = new Bundle();
        args.putString(city_name, city.getName());
        args.putString(city_province, city.getProvince());
        args.putInt(city_pos, position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String cityName = getArguments().getString(city_name);
        String cityProvince = getArguments().getString(city_province);
        position = getArguments().getInt(city_pos);

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_edit_city, null);

        cityNameEditText = view.findViewById(R.id.cityNameEditText);
        cityProvinceEditText = view.findViewById(R.id.cityProvinceEditText);
        Button saveButton = view.findViewById(R.id.saveButton);

        cityNameEditText.setText(cityName);
        cityProvinceEditText.setText(cityProvince);

        saveButton.setOnClickListener(v -> {
            String newCityName = cityNameEditText.getText().toString();
            String newCityProvince = cityProvinceEditText.getText().toString();

            City updatedCity = new City(newCityName, newCityProvince);
            ((MainActivity) getActivity()).updateCity(updatedCity, position);

            dismiss();
        });

        return new AlertDialog.Builder(getActivity())
                .setTitle("Edit City")
                .setView(view)
                .create();
    }
}

