package com.example.airplanereservationsystem;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.airplanereservationsystem.DB.AppDatabase;

import java.util.Date;

@Entity(tableName = AppDatabase.FLIGHTLOG_TABLE)
public class FlightLog {

    @PrimaryKey
    @NonNull
    String mFlightNum;

    String mDeparture;
    String mArrival;
    String mDepartureTime;
    Integer mCapacity;
    Double mPrice;
    //flight number, departure/arrival information, departure time, flight capacity, and price information.

    public FlightLog(String flightNum, String departure, String arrival, String departureTime, Integer capacity, Double price) {
        this.mFlightNum = flightNum;
        this.mDeparture = departure;
        this.mArrival = arrival;
        this.mDepartureTime = departureTime;
        this.mCapacity = capacity;
        this.mPrice = price;
    }

    @NonNull
    public String getFlightNum() {
        return mFlightNum;
    }

    public void setFlightNum(@NonNull String mFlightNum) {
        this.mFlightNum = mFlightNum;
    }

    public String getDeparture() {
        return mDeparture;
    }

    public void setDeparture(String mDeparture) {
        this.mDeparture = mDeparture;
    }

    public String getArrival() {
        return mArrival;
    }

    public void setArrival(String mArrival) {
        this.mArrival = mArrival;
    }

    public String getDepartureTime() {
        return mDepartureTime;
    }

    public void setDepartureTime(String mDepartureTime) {
        this.mDepartureTime = mDepartureTime;
    }

    public Integer getCapacity() {
        return mCapacity;
    }

    public void setCapacity(Integer mCapacity) {
        this.mCapacity = mCapacity;
    }

    public Double getPrice() {
        return mPrice;
    }

    public void setPrice(Double mPrice) {
        this.mPrice = mPrice;
    }

    @Override
    public String toString() {
        return '\n' + "Flight Number: " + mFlightNum + '\n' +
                "Departure: " + mDeparture + '\n' +
                "Arrival: " + mArrival + '\n' +
                "Departure Time: " + mDepartureTime + '\n' +
                "Capacity: " + mCapacity + '\n' +
                "Price: " + mPrice + '\n';
    }
}
