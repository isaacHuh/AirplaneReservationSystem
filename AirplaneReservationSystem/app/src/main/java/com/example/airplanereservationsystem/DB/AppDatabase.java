package com.example.airplanereservationsystem.DB;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.airplanereservationsystem.DB.typecoverter.DateTypeConverter;
import com.example.airplanereservationsystem.AccountLog;
import com.example.airplanereservationsystem.FlightLog;
import com.example.airplanereservationsystem.ReservationLog;
import com.example.airplanereservationsystem.TransactionLog;

@Database(entities = {AccountLog.class, ReservationLog.class, FlightLog.class, TransactionLog.class},version = 1)
@TypeConverters(DateTypeConverter.class)
public abstract class AppDatabase extends RoomDatabase {
    public static final String DBNAME = "db-airplanesystem";
    public static final String ACCOUNTLOG_TABLE = "accountlog";
    public static final String RESERVATIONLOG_TABLE = "reservationlog";
    public static final String FLIGHTLOG_TABLE = "flightlog";
    public static final String TRANSACTIONLOG_TABLE = "transactionlog";
    public abstract AccountLogDAO getAccountLogDOA();
    public abstract ReservationLogDAO getReservationLogDOA();
    public abstract FlightLogDAO getFlightLogDOA();
    public abstract TransactionLogDAO getTransactionLogDOA();
}
