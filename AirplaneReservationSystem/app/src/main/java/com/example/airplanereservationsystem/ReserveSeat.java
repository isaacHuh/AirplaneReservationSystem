package com.example.airplanereservationsystem;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.airplanereservationsystem.DB.AppDatabase;
import com.example.airplanereservationsystem.DB.FlightLogDAO;
import com.example.airplanereservationsystem.DB.ReservationLogDAO;
import com.example.airplanereservationsystem.DB.TransactionLogDAO;

import java.util.List;

import static androidx.appcompat.app.AlertDialog.*;

public class ReserveSeat extends AppCompatActivity {
    public static FlightLog currFlight;
    public static String currUser;
    public static int numSeats;
    double cost = currFlight.getPrice()*numSeats;

    TextView flightDisplay;
    Button reserveSeatsButton;

    ReservationLogDAO mReservationLogDAO;
    FlightLogDAO mFlightLogDAO;
    TransactionLogDAO mTransactionLogDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve_seat);

        getSupportActionBar().setTitle("Reserve Seats");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mReservationLogDAO = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DBNAME)
                .allowMainThreadQueries()
                .build()
                .getReservationLogDOA();

        mFlightLogDAO = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DBNAME)
                .allowMainThreadQueries()
                .build()
                .getFlightLogDOA();

        mTransactionLogDAO = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DBNAME)
                .allowMainThreadQueries()
                .build()
                .getTransactionLogDOA();

        flightDisplay = findViewById(R.id.FlightTextView);
        reserveSeatsButton = findViewById(R.id.MakeReservationButton);

        flightDisplay.setText(currFlight.toString() + "\n\nNumber of Seats: " + numSeats + "\nTotal Cost: " + cost);

        reserveSeatsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //createReservation();
                AlertDialog alert = confirm();
                alert.show();
            }
        });

    }

    private void createReservation(){
        ReservationLog reservationLog = new ReservationLog(currUser,currFlight.getFlightNum(),currFlight.getDeparture(),currFlight.getArrival(),numSeats,cost);
        mReservationLogDAO.insert(reservationLog);

        TransactionLog transactionLog = new TransactionLog("Reserve Seat", reservationLog.getUsername(), reservationLog.getFlightNum(), reservationLog.getDeparture(),
                reservationLog.getArrival(), currFlight.getDepartureTime(), reservationLog.getNumTickets(), reservationLog.getReservationNum(), cost);
        mTransactionLogDAO.insert(transactionLog);

        currFlight.setCapacity(currFlight.getCapacity()-numSeats);
        mFlightLogDAO.update(currFlight);

        Toast.makeText(this, "Flight Reserved", Toast.LENGTH_LONG).show();

        Intent intent = new Intent(this, SearchFlights.class);
        startActivity(intent);

    }

    private AlertDialog confirm(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to reserve " + numSeats + " seats for $" + cost + "?")
                .setTitle("Confirm Flight")
                .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // CONFIRM
                        createReservation();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // CANCEL
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
