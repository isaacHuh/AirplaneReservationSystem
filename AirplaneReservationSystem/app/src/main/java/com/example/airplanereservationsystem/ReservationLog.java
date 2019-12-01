package com.example.airplanereservationsystem;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.airplanereservationsystem.DB.AppDatabase;

import java.util.Date;

@Entity(tableName = AppDatabase.RESERVATIONLOG_TABLE)
public class ReservationLog {

    @PrimaryKey(autoGenerate = true)
    int mReservationNum;

    String mUsername;
    String mFlightNum;
    String mDeparture;
    String mArrival;
    int mNumTickets;
    Double mCost;
    Date mDate;

    //a total amount, and current date/time.
    public ReservationLog(String username, String flightNum, String departure, String arrival, int numTickets, Double cost) {
        this.mUsername = username;
        this.mFlightNum = flightNum;
        this.mDeparture = departure;
        this.mArrival = arrival;
        this.mNumTickets = numTickets;
        this.mCost = cost;
        this.mDate = new Date();
    }

    public int getReservationNum() {
        return mReservationNum;
    }

    public void setReservationNum(int mReservationNum) {
        this.mReservationNum = mReservationNum;
    }

    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String mUsername) {
        this.mUsername = mUsername;
    }

    public String getFlightNum() {
        return mFlightNum;
    }

    public void setFlightNum(String mFlightNum) {
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

    public int getNumTickets() {
        return mNumTickets;
    }

    public void setNumTickets(int mNumTickets) {
        this.mNumTickets = mNumTickets;
    }

    public Double getCost() {
        return mCost;
    }

    public void setCost(Double mCost) {
        this.mCost = mCost;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date mDate) {
        this.mDate = mDate;
    }

    @Override
    public String toString() {
        return "ReservationLog{" +
                "mReservationNum=" + mReservationNum +
                ", mUsername='" + mUsername + '\'' +
                ", mFlightNum='" + mFlightNum + '\'' +
                ", mDeparture='" + mDeparture + '\'' +
                ", mArrival='" + mArrival + '\'' +
                ", mNumTickets=" + mNumTickets +
                ", mCost=" + mCost +
                ", mDate=" + mDate +
                '}';
    }
}
