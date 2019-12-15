package com.example.airplanereservationsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainScreen extends AppCompatActivity {
    public static String curr_user;

    Button CreateAccountBtn, ReserveSeatBtn, CancelReservationBtn, ManageSystemBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        getSupportActionBar().setTitle("Otter Airways");

        CreateAccountBtn = findViewById(R.id.CreateAccountButton);
        ReserveSeatBtn = findViewById(R.id.ReserveSeatButton);
        CancelReservationBtn = findViewById(R.id.CancelReservationButton);
        ManageSystemBtn = findViewById(R.id.ManageSystemButton);

        CreateAccountBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                openCreateAccount();
            }
        });

        ReserveSeatBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                openSearchFlight();
            }
        });

        CancelReservationBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                openCancelReservation();
            }
        });

        ManageSystemBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                openManageSystem();
            }
        });
    }

    private void openCreateAccount(){
        Intent intent = new Intent(this, CreateAccount.class);
        startActivity(intent);
    }

    private void openSearchFlight(){
        Intent intent = new Intent(this, SearchFlights.class);
        startActivity(intent);
    }

    private void openCancelReservation(){
        Intent intent = new Intent(this, LoginCancelReservation.class);
        startActivity(intent);
    }

    private void openManageSystem(){
        Intent intent = new Intent(this, LoginManageSystem.class);
        startActivity(intent);
    }
}
