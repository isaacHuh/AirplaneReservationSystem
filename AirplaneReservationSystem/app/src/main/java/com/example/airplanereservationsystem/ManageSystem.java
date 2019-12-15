package com.example.airplanereservationsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ManageSystem extends AppCompatActivity {

    Button viewTransactionsBtn;
    Button viewFlightsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_system);

        getSupportActionBar().setTitle("Manage System");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewTransactionsBtn = findViewById(R.id.ReservationDisplayButton);
        viewFlightsBtn = findViewById(R.id.FlightDisplayButton);

        viewTransactionsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openReservationDisplay();
            }
        });

        viewFlightsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFlightDisplay();
            }
        });
    }

    private void openReservationDisplay(){
        Intent intent = new Intent(this, TransactionDisplay.class);
        startActivity(intent);
    }

    private void openFlightDisplay(){
        Intent intent = new Intent(this, FlightDisplay.class);
        startActivity(intent);
    }
}
