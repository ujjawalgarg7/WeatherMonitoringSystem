package com.example.weather;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button refreshButton ;
    TextView tempView;
    TextView humidView ;
    private int UPDATE_INTERVAL = 2*60*1000;
    private Handler handler;
    private Runnable weatherUpdater;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        refreshButton = (Button) findViewById(R.id.refresh);
        tempView = (TextView) findViewById(R.id.temp);
        humidView = (TextView) findViewById(R.id.humid);

        handler = new Handler();

        weatherUpdater = new Runnable(){
            @Override
            public void run(){
                updateWeather();
                handler.postDelayed(this,UPDATE_INTERVAL);
            }
        };

        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               updateWeather();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        updateWeather();
    }

    private void updateWeather(){
        int temp = new Random().nextInt((50 - 15) + 1) + 15;
        tempView.setText("Temperature : "+temp+"°C");
        int humid = new Random().nextInt((100 - 1) + 1);
        humidView.setText("Humidity : "+humid+"%");
        Toast.makeText(this,"Weather Updated",Toast.LENGTH_SHORT).show();
    }
}