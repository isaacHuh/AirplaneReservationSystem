package com.example.airplanereservationsystem;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.airplanereservationsystem.DB.AppDatabase;
import com.example.airplanereservationsystem.DB.FlightLogDAO;

import java.util.List;

public class CreateFlight extends AppCompatActivity {
    boolean valid = false;

    EditText mFlightNum;
    EditText mDeparture;
    EditText mArrival;
    EditText mDepartureTime;
    EditText mCapacity; //Integer
    EditText mPrice; //Double

    Button mSubmit;

    FlightLogDAO mFlightLogDAO;
    List<FlightLog> mFlightLogs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_flight);

        getSupportActionBar().setTitle("Create Flight");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mFlightNum = findViewById(R.id.FlightNumText);
        mDeparture = findViewById(R.id.DepartureSearchText);
        mArrival = findViewById(R.id.ArrivalSearchText);
        mDepartureTime = findViewById(R.id.DepartureTimeText);
        mCapacity = findViewById(R.id.CapacityText);
        mPrice = findViewById(R.id.PriceText);

        mSubmit = findViewById(R.id.CreateFlightButton);

        mFlightLogDAO = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DBNAME)
                .allowMainThreadQueries()
                .build()
                .getFlightLogDOA();

        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                createFlight();
                if(valid){
                    openFlightDisplay();
                }
            }
        });
    }

    private void openFlightDisplay(){
        Intent intent = new Intent(this, FlightDisplay.class);
        startActivity(intent);
    }

    private void createFlight(){
        String message = "Flight Created.";

        String flightNum = mFlightNum.getText().toString();
        String departure = mDeparture.getText().toString();
        String arrival = mArrival.getText().toString();
        String departureTime = mDepartureTime.getText().toString();

        Integer capacity = -1;
        if(!mCapacity.getText().toString().isEmpty()){
            capacity = Integer.parseInt(mCapacity.getText().toString());
        }

        Double price = -1.0;
        if(!mPrice.getText().toString().isEmpty()) {
            price = Double.parseDouble(mPrice.getText().toString());
        }

        if(!flightNum.isEmpty()) {
            FlightLog flight = mFlightLogDAO.getFlightLogByFlightNum(flightNum);

            if (flight == null) {
                if(!departure.isEmpty() && !arrival.isEmpty() && !departureTime.isEmpty() && capacity != -1 && price != 1.0) {

                    FlightLog flightLog = new FlightLog(flightNum, departure, arrival, departureTime, capacity, price);
                    mFlightLogDAO.insert(flightLog);
                    valid = true;

                }else{
                    message = "Invalid Inputs.";
                }
            }else{
                message = "Invalid Flight. Flight Already Exists.";
            }
        }else{
            message = "Invalid Inputs.";
        }

        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

}

    /*
    private void refreshDisplay(){
        mFlightLogs = mFlightLogDAO.getAllFlightLogs();

        if(!mFlightLogs.isEmpty()){
            StringBuilder stringBuilder = new StringBuilder();

            for(FlightLog flightLog : mFlightLogs){
                stringBuilder.append(flightLog.toString());
            }

            mMainDisplay.setText(stringBuilder.toString());
        }else{
            mMainDisplay.setText("No Flights Yet.");
        }
    }*/

