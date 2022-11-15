package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;

public class WeatherActivity extends AppCompatActivity {
    TextView currentDate;
    TextView temperature;
    TextView description;
    ImageView weatherIcon;

    protected void onCreate(Bundle savedInstanceState) {
        ImageButton buttonWeather;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        buttonWeather = findViewById(R.id.buttonBackWeather);
        currentDate = findViewById(R.id.current_date);
        temperature = findViewById(R.id.temperature);
        description = findViewById(R.id.description);
        weatherIcon = findViewById(R.id.weather_icon);

        buttonWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( WeatherActivity.this, MenuActivity.class);
                startActivity(intent);
                //setContentView(R.layout.activity_main);
            }
        });
    }



    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onResume() {
        super.onResume();
        LocalDate date = LocalDate.now();
        String month = translateDate(date.getMonth().toString());
        currentDate.setText(date.getDayOfMonth() + " " + month);
        String key = "39f5f0945947f0b73d31d88dcd59b0dd";
        String url = "https://api.openweathermap.org/data/2.5/weather?q=Красноярск&appid=" + key + "&units=metric&lang=ru";
        new GetURLData().execute(url);

    }

    protected String translateDate (String month){
        String monthTranslated = "";
        if (month.equals("MAY")) monthTranslated = "Мая";
        return monthTranslated;

    }

    @SuppressLint("StaticFieldLeak")
    private class GetURLData extends AsyncTask<String, String, String> {

        // Будет выполнено до отправки данных по URL
        protected void onPreExecute() {
            super.onPreExecute();
            temperature.setText("...");
            description.setText("...");
        }

        // Будет выполняться во время подключения по URL
        @Override
        protected String doInBackground(String... strings) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                // Создаем URL подключение, а также HTTP подключение
                URL url = new URL(strings[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                // Создаем объекты для считывания данных из файла
                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));

                // Генерируемая строка
                StringBuilder buffer = new StringBuilder();
                String line = "";

                // Считываем файл и записываем все в строку
                while((line = reader.readLine()) != null)
                    buffer.append(line).append("\n");

                // Возвращаем строку
                return buffer.toString();

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                // Закрываем соединения
                if(connection != null)
                    connection.disconnect();

                try {
                    if (reader != null)
                        reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return null;
        }

        // Выполняется после завершения получения данных
        @SuppressLint("SetTextI18n")
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            // Конвертируем JSON формат и выводим данные в текстовом поле
            try {
                JSONObject jsonObject = new JSONObject(result);
                temperature.setText((int) jsonObject.getJSONObject("main").getDouble("temp") + "°С");
                String desc = jsonObject.getJSONArray("weather").getJSONObject(0).getString("description");
                description.setText(desc);
                if (desc.equals("ясно")){
                    weatherIcon.setImageResource(R.drawable.weather_sunny);
                }
                //System.out.println("PIP " + jsonObject.getJSONArray("weather").getJSONObject(0).getString("description"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

}
