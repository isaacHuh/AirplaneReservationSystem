package com.example.airplanereservationsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import androidx.room.Transaction;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.airplanereservationsystem.DB.AppDatabase;
import com.example.airplanereservationsystem.DB.ReservationLogDAO;
import com.example.airplanereservationsystem.DB.TransactionLogDAO;

import java.util.ArrayList;
import java.util.List;

public class TransactionDisplay extends AppCompatActivity {
    private ListView lstTransactions;

    TransactionLogDAO mTransactionLogDAO;
    List<TransactionLog> mTransactionLogs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_display);

        getSupportActionBar().setTitle("Transaction Logs");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        lstTransactions = findViewById(R.id.TransactionDisplayList);

        mTransactionLogDAO = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DBNAME)
                .allowMainThreadQueries()
                .build()
                .getTransactionLogDOA();

        refreshDisplay();
    }

    private void refreshDisplay(){
        mTransactionLogs = mTransactionLogDAO.getAllTransactionLogs();

        if(!mTransactionLogs.isEmpty()){
            ArrayList<String> arrayList = new ArrayList<>();

            for(TransactionLog transactionLog : mTransactionLogs){
                arrayList.add(transactionLog.toString());
            }

            ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList);
            lstTransactions.setAdapter(adapter);
        }
    }
}
