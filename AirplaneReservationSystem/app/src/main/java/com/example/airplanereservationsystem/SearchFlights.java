package com.example.airplanereservationsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.airplanereservationsystem.DB.AppDatabase;
import com.example.airplanereservationsystem.DB.FlightLogDAO;

import java.util.ArrayList;
import java.util.List;

public class SearchFlights extends AppCompatActivity {
    private ListView lstFlights;
    boolean valid = true;

    EditText departure;
    EditText arrival;
    EditText seats;

    FlightLogDAO mFlightLogDAO;
    List<FlightLog> mFlightLogs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_flights);

        getSupportActionBar().setTitle("Search Flights");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        lstFlights = findViewById(R.id.FlightFindList);
        departure = findViewById(R.id.DepartureSearchText);
        arrival = findViewById(R.id.ArrivalSearchText);
        seats = findViewById(R.id.SeatsSearchText);
        //fab = findViewById(R.id.AddFAButton);

        mFlightLogDAO = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DBNAME)
                .allowMainThreadQueries()
                .build()
                .getFlightLogDOA();

        refreshDisplay();

        departure.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                refreshDisplay();
            }
        });

        arrival.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                refreshDisplay();
            }
        });

        seats.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                refreshDisplay();
            }
        });

        lstFlights.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (valid) {
                    FlightLog flightLog = mFlightLogs.get(i);

                    String flightNum = flightLog.getFlightNum();

                    //flightNumDeleteSelected = flightNum;
                    //Log.i("tag", ".."+flightNum+"..");

                    ReserveSeat.currFlight = flightLog;
                    openReserveSeat();
                }
            }
        });
    }

    private void openReserveSeat(){
        int seatsSearch = 0;
        if(!seats.getText().toString().isEmpty()) {
            seatsSearch = Integer.parseInt(seats.getText().toString());

            if(seatsSearch > 0 && seatsSearch <= 7) {
                ReserveSeat.numSeats = seatsSearch;
                Intent intent = new Intent(this, LoginReserveSeat.class);
                startActivity(intent);
            }else{
                Toast.makeText(this, "Invalid number of seats selected.\nCan only purchase up to 7 tickets.", Toast.LENGTH_LONG).show();
            }
        }else{
            Toast.makeText(this, "Number of seats must be inputted.", Toast.LENGTH_LONG).show();
        }
    }

    private void refreshDisplay(){
        String departureSearch = "%" + departure.getText().toString() + "%";
        String arrivalSearch = "%" + arrival.getText().toString() + "%";

        int seatsSearch = 0;
        if(!seats.getText().toString().isEmpty()) {
            seatsSearch = Integer.parseInt(seats.getText().toString());
        }

        mFlightLogs = mFlightLogDAO.getFlightsByInputs(departureSearch, arrivalSearch, seatsSearch);

        ArrayList<String> arrayList = new ArrayList<>();
        if(!mFlightLogs.isEmpty()){
            valid = true;
            for(FlightLog flightLog : mFlightLogs){
                arrayList.add(flightLog.toString());
            }
        }else{
            valid = false;
            arrayList.add("There are no flights available.");
        }

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList);
        lstFlights.setAdapter(adapter);
        registerForContextMenu(lstFlights);
    }
}
