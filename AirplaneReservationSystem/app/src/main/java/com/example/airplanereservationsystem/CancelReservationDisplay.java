package com.example.airplanereservationsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.airplanereservationsystem.DB.AppDatabase;
import com.example.airplanereservationsystem.DB.ReservationLogDAO;

import java.util.ArrayList;
import java.util.List;

public class CancelReservationDisplay extends AppCompatActivity {
    public static String currUser;
    private ListView lstReservations;
    boolean valid = true;

    ReservationLogDAO mReservationLogDAO;
    List<ReservationLog> mReservationLogs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancel_reservation_display);

        getSupportActionBar().setTitle("Reservation Logs");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        lstReservations = findViewById(R.id.CancelReservationDisplayList);

        mReservationLogDAO = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DBNAME)
                .allowMainThreadQueries()
                .build()
                .getReservationLogDOA();

        refreshDisplay();

        lstReservations.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(valid) {
                    ReservationLog reservationLog = mReservationLogs.get(i);
                    CancelReservation.reservationLog = reservationLog;
                    openCancelReservation();
                }
            }
        });

    }

    private void openCancelReservation(){
        Intent intent = new Intent(this, CancelReservation.class);
        startActivity(intent);
    }

    private void refreshDisplay(){
        mReservationLogs = mReservationLogDAO.getUserReservationLogs(currUser);

        ArrayList<String> arrayList = new ArrayList<>();
        if(!mReservationLogs.isEmpty()){
            valid = true;
            for(ReservationLog reservationLog : mReservationLogs){
                arrayList.add(reservationLog.toString());
            }

        }else{
            arrayList.add("No reservations exits.");
            valid = false;
        }

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList);
        lstReservations.setAdapter(adapter);
    }
}
