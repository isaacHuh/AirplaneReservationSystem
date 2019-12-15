package com.example.airplanereservationsystem;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.airplanereservationsystem.DB.AppDatabase;

import java.util.Date;

@Entity(tableName = AppDatabase.TRANSACTIONLOG_TABLE)
public class TransactionLog {

    @PrimaryKey(autoGenerate = true)
    int mTransactionNum;

    String mType;
    String mUsername;
    String mFlightNum;
    String mDeparture;
    String mArrival;
    String mDepartureTime;
    int mNumTickets;
    int mReservationNum;
    Double mTotalAmount;
    Date mDate;


    //a total amount, and current date/time.
    /*
    public TransactionLog(String type, String username) {
        this.mUsername = username;
        this.mType = type;
        mDate = new Date();
    }*/

    public TransactionLog(String type, String username, String flightNum, String departure, String arrival, String departureTime, int numTickets, int reservationNum, Double totalAmount) {
        this.mType = type;
        this.mUsername = username;
        this.mFlightNum = flightNum;
        this.mDeparture = departure;
        this.mArrival = arrival;
        this.mDepartureTime = departureTime;
        this.mNumTickets = numTickets;
        this.mReservationNum = reservationNum;
        this.mTotalAmount = totalAmount;
        this.mDate = new Date();
    }
    /*
    public TransactionLog(String type, String username, String flightNum, String departure, String arrival, int numTickets, int reservationNum) {
        this.mType = type;
        this.mUsername = username;
        this.mFlightNum = flightNum;
        this.mDeparture = departure;
        this.mArrival = arrival;
        this.mNumTickets = numTickets;
        this.mReservationNum = reservationNum;
        mDate = new Date();
    }*/

    public int getTransactionNum() {
        return mTransactionNum;
    }

    public void setTransactionNum(int mTransactionNum) {
        this.mTransactionNum = mTransactionNum;
    }

    public String getType() {
        return mType;
    }

    public void setType(String mType) {
        this.mType = mType;
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

    public String getDepartureTime() {
        return mDepartureTime;
    }

    public void setDepartureTime(String mDepartureTime) {
        this.mDepartureTime = mDepartureTime;
    }

    public int getNumTickets() {
        return mNumTickets;
    }

    public void setNumTickets(int mNumTickets) {
        this.mNumTickets = mNumTickets;
    }

    public int getReservationNum() {
        return mReservationNum;
    }

    public void setReservationNum(int mReservationNum) {
        this.mReservationNum = mReservationNum;
    }

    public Double getTotalAmount() {
        return mTotalAmount;
    }

    public void setTotalAmount(Double mTotalAmount) {
        this.mTotalAmount = mTotalAmount;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date mDate) {
        this.mDate = mDate;
    }

    @Override
    public String toString() {
        String displayMessage = "\nTransaction Log: \n" +
                "Transaction Number: " + mTransactionNum + "\n" +
                "Transaction Type: " + mType + "\n" +
                "-------------------------------------------------------" + "\n";


        if(mType.equals("Create Account")){
            displayMessage += "Username: " + mUsername + '\n' +
                    "Date: " + mDate;
        }
        if(mType.equals("Reserve Seat")){
            displayMessage += "Username: " + mUsername + '\n' +
                    "Reservation Number: " + mReservationNum + "\n" +
                    "Flight Number" + mFlightNum + '\n' +
                    "Departure: " + mDeparture + '\n' +
                    "Arrival: " + mArrival + '\n' +
                    "Departure Time: " + mDepartureTime + '\n' +
                    "Number of Tickets: " + mNumTickets + "\n" +
                    "Total Amount: " + mTotalAmount + "\n" +
                    "Date: " + mDate;
        }
        if(mType.equals("Cancellation")){
            displayMessage += "Username: " + mUsername + '\n' +
                    "Reservation Number: " + mReservationNum + "\n" +
                    "Flight Number" + mFlightNum + '\n' +
                    "Departure: " + mDeparture + '\n' +
                    "Arrival: " + mArrival + '\n' +
                    "Departure Time: " + mDepartureTime + '\n' +
                    "Number of Tickets: " + mNumTickets + "\n" +
                    "Date: " + mDate;
        }

        displayMessage += "\n";


        return displayMessage;
    }
}
