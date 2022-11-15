package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ImageButton buttonMenu;
        ImageButton buttonToNotes;
        ImageButton buttonToMap;
        ImageButton buttonToWeather;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        buttonMenu = findViewById(R.id.buttonBack);
        buttonToNotes = findViewById(R.id.buttonToNotes);
        buttonToMap = findViewById(R.id.buttonToMap);
        buttonToWeather = findViewById(R.id.buttonToWeather);

        buttonMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( MenuActivity.this, MainActivity.class);
                startActivity(intent);
                //setContentView(R.layout.activity_main);
            }
        });

        buttonToNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( MenuActivity.this, NotesActivity.class);
                startActivity(intent);
                //setContentView(R.layout.activity_main);
            }
        });

        buttonToMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( MenuActivity.this, MapActivity.class);
                startActivity(intent);
                //setContentView(R.layout.activity_main);
            }
        });

        buttonToWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( MenuActivity.this, WeatherActivity.class);
                startActivity(intent);
                //setContentView(R.layout.activity_main);
            }
        });
    }
}
