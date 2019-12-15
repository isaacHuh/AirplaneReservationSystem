package com.example.airplanereservationsystem.DB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.airplanereservationsystem.FlightLog;
import com.example.airplanereservationsystem.ReservationLog;

import java.util.List;

@Dao
public interface FlightLogDAO {
    @Insert
    void insert(FlightLog... flightlogs);

    @Update
    void update(FlightLog... flightlogs);

    @Delete
    void delete(FlightLog flightLog);

    @Query("SELECT * FROM " + AppDatabase.FLIGHTLOG_TABLE + " WHERE mFlightNum = :flightNum")
    FlightLog getFlightLogByFlightNum(String flightNum);

    @Query("SELECT * FROM " + AppDatabase.FLIGHTLOG_TABLE + " WHERE (mDeparture LIKE :departure) AND (mArrival LIKE :arrival) AND (mCapacity >= :seats)")
    List<FlightLog> getFlightsByInputs(String departure, String arrival, int seats);

    @Query("SELECT * FROM " + AppDatabase.FLIGHTLOG_TABLE)
    List<FlightLog> getAllFlightLogs();

    @Query("DELETE FROM " + AppDatabase.FLIGHTLOG_TABLE)
    void deleteAll();
}
