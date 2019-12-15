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
import android.widget.ListView;
import android.widget.Toast;

import com.example.airplanereservationsystem.DB.AppDatabase;
import com.example.airplanereservationsystem.DB.FlightLogDAO;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class FlightDisplay extends AppCompatActivity {

    private ListView lstFlights;
    private FloatingActionButton fab;

    FlightLogDAO mFlightLogDAO;
    List<FlightLog> mFlightLogs;

    String flightNumDeleteSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_display);

        getSupportActionBar().setTitle("Flights");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        lstFlights = findViewById(R.id.FlightsList);
        fab = findViewById(R.id.AddFAButton);

        mFlightLogDAO = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DBNAME)
                .allowMainThreadQueries()
                .build()
                .getFlightLogDOA();

        refreshDisplay();

        lstFlights.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                FlightLog flightLog = mFlightLogs.get(i);
                String flightNum = flightLog.getFlightNum();

                flightNumDeleteSelected = flightNum;
                Log.i("tag", ".."+flightNum+"..");
                return false;
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCreateFlight();
            }
        });
    }

    private void openCreateFlight(){
        Intent intent = new Intent(this, CreateFlight.class);
        startActivity(intent);
    }

    private void refreshDisplay(){
        mFlightLogs = mFlightLogDAO.getAllFlightLogs();

        if(!mFlightLogs.isEmpty()){
            ArrayList<String> arrayList = new ArrayList<>();

            for(FlightLog flightLog : mFlightLogs){
                arrayList.add(flightLog.toString());
            }

            ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList);
            lstFlights.setAdapter(adapter);
            registerForContextMenu(lstFlights);
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.main_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        FlightLog flight = mFlightLogDAO.getFlightLogByFlightNum(flightNumDeleteSelected);
        mFlightLogDAO.delete(flight);
        refreshDisplay();
        return true;
        //return super.onContextItemSelected(item);
    }
}
