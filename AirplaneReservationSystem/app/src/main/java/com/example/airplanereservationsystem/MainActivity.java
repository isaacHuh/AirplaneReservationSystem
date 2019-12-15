package com.example.airplanereservationsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;

import com.example.airplanereservationsystem.DB.AccountLogDAO;
import com.example.airplanereservationsystem.DB.AppDatabase;
import com.example.airplanereservationsystem.DB.FlightLogDAO;

public class MainActivity extends AppCompatActivity {
    //public static final String EXTRA_USERNAME = "com.example.airplanereservationsystem.example.EXTRA_USERNAME";
    //public static final String EXTRA_PASSWORD = "com.example.airplanereservationsystem.example.EXTRA_PASSWORD";

    FlightLogDAO mFlightLogDAO;
    AccountLogDAO mAccountLogDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mFlightLogDAO = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DBNAME)
                .allowMainThreadQueries()
                .build()
                .getFlightLogDOA();

        mAccountLogDAO = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DBNAME)
                .allowMainThreadQueries()
                .build()
                .getAccountLogDOA();

        if(mAccountLogDAO.getAllAccountLogs().isEmpty()){
            populateDB();
        }

        Intent intent = new Intent(this, MainScreen.class);
        startActivity(intent);
    }

    private void populateDB(){
        mAccountLogDAO.insert(new AccountLog("alice5", "csumb100"),
                new AccountLog("brian77","123ABC"),
                new AccountLog("chris21", "CHRIS21"));

        mFlightLogDAO.insert(new FlightLog("Otter101", "Monterey", "Los Angeles", "10:00(AM)",10, 150.0),
                new FlightLog("Otter102", "Los Angeles", "Monterey", "1:00(PM)",10,150.0),
                new FlightLog("Otter201", "Monterey", "Seattle", "11:00(AM)",5,200.5),
                new FlightLog("Otter205", "Monterey", "Seattle", "3:00(PM)",15,150.0),
                new FlightLog("Otter202","Seattle", "Monterey", "2:00(PM)",5,200.5));
    }

}
