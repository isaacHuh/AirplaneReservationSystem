package com.example.airplanereservationsystem.DB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.airplanereservationsystem.ReservationLog;
import com.example.airplanereservationsystem.TransactionLog;

import java.util.List;

@Dao
public interface TransactionLogDAO {
    @Insert
    void insert(TransactionLog... transactionlogs);

    @Update
    void update(TransactionLog... transactionlogs);

    @Delete
    void delete(TransactionLog transactionLog);


    @Query("SELECT * FROM " + AppDatabase.TRANSACTIONLOG_TABLE)
    List<TransactionLog> getAllTransactionLogs();

    @Query("DELETE FROM " + AppDatabase.TRANSACTIONLOG_TABLE)
    void deleteAll();
}
