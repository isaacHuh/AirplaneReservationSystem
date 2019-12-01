package com.example.airplanereservationsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    //public static final String EXTRA_USERNAME = "com.example.airplanereservationsystem.example.EXTRA_USERNAME";
    //public static final String EXTRA_PASSWORD = "com.example.airplanereservationsystem.example.EXTRA_PASSWORD";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this, MainScreen.class);
        startActivity(intent);
    }

}
