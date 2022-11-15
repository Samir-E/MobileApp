package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {
    GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ImageButton buttonMap;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sport);

        buttonMap = findViewById(R.id.buttonBackMap);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        buttonMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( MapActivity.this, MenuActivity.class);
                startActivity(intent);
                //setContentView(R.layout.activity_main);
            }
        });
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        map = googleMap;

        LatLng Krasnoyarsk1 = new LatLng(56.01444607109033, 92.85120534773274);
        LatLng Krasnoyarsk2 = new LatLng(56.0118495589454, 92.85594943764859);
        LatLng Krasnoyarsk3 = new LatLng(56.01607922420441, 92.84508720063793);
        LatLng Krasnoyarsk4 = new LatLng(56.015808593838926, 92.86015899110171);
        LatLng KrasnoyarskPoint = new LatLng(56.0137694,92.8527403);

        map.addMarker(new MarkerOptions().position(Krasnoyarsk1).title("Экзотика"));
        map.addMarker(new MarkerOptions().position(Krasnoyarsk2).title("Богатырь"));
        map.addMarker(new MarkerOptions().position(Krasnoyarsk3).title("Level UP"));
        map.addMarker(new MarkerOptions().position(Krasnoyarsk4).title("Официальный тренировочный центр Strongo Hammer Strength"));
        //map.moveCamera(CameraUpdateFactory.newLatLng(Krasnoyarsk));
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(KrasnoyarskPoint, 14));
    }
}
