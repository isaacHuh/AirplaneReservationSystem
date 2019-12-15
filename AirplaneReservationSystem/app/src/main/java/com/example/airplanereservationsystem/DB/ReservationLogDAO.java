package com.example.airplanereservationsystem.DB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.airplanereservationsystem.AccountLog;
import com.example.airplanereservationsystem.ReservationLog;

import java.util.List;

@Dao
public interface ReservationLogDAO {
    @Insert
    void insert(ReservationLog... reservationlogs);

    @Update
    void update(ReservationLog... reservationlogs);

    @Delete
    void delete(ReservationLog reservationLog);

    @Query("SELECT * FROM " + AppDatabase.RESERVATIONLOG_TABLE + " WHERE mUsername = :username")
    List<ReservationLog> getUserReservationLogs(String username);

    @Query("SELECT * FROM " + AppDatabase.RESERVATIONLOG_TABLE)
    List<ReservationLog> getAllReservationLogs();

    @Query("DELETE FROM " + AppDatabase.RESERVATIONLOG_TABLE)
    void deleteAll();
}
