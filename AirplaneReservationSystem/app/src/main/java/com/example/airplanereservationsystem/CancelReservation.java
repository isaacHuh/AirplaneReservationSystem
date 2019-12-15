package com.example.airplanereservationsystem;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.airplanereservationsystem.DB.AppDatabase;
import com.example.airplanereservationsystem.DB.FlightLogDAO;
import com.example.airplanereservationsystem.DB.ReservationLogDAO;
import com.example.airplanereservationsystem.DB.TransactionLogDAO;

public class CancelReservation extends AppCompatActivity {
    public static ReservationLog reservationLog;

    TextView reservationDisplay;
    Button cancelButton;

    ReservationLogDAO mReservationLogDAO;
    FlightLogDAO mFlightLogDAO;
    TransactionLogDAO mTransactionLogDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancel_reservation);

        getSupportActionBar().setTitle("Cancel Flight");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        reservationDisplay = findViewById(R.id.ReservationToCancelView);
        cancelButton = findViewById(R.id.RemoveReservationButton);
        reservationDisplay.setText(reservationLog.toString());

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

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog alert = confirm();
                confirm().show();
            }
        });
    }

    private void removeReservation(){
        FlightLog flightLog = mFlightLogDAO.getFlightLogByFlightNum(reservationLog.getFlightNum());
        flightLog.setCapacity(flightLog.getCapacity()+reservationLog.getNumTickets());

        TransactionLog transactionLog = new TransactionLog("Cancellation", reservationLog.getUsername(), reservationLog.getFlightNum(), reservationLog.getDeparture(),
                                                            reservationLog.getArrival(), flightLog.getDepartureTime(), reservationLog.getNumTickets(), reservationLog.getReservationNum(), 0.0);
        mTransactionLogDAO.insert(transactionLog);

        mFlightLogDAO.update(flightLog);
        mReservationLogDAO.delete(reservationLog);

        Toast.makeText(this, "Reservation Cancelled", Toast.LENGTH_LONG).show();

        Intent intent = new Intent(this, MainScreen.class);
        startActivity(intent);
    }


    private AlertDialog confirm(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to cancel this flight?")
                .setTitle("Confirm Cancellation")
                .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // CONFIRM
                        removeReservation();
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
