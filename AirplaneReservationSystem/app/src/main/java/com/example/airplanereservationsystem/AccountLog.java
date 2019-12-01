package com.example.airplanereservationsystem;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.airplanereservationsystem.DB.AppDatabase;


@Entity(tableName = AppDatabase.ACCOUNTLOG_TABLE)
public class AccountLog {

    @PrimaryKey
    @NonNull
    public String mUsername;

    public String mPassword;

    public AccountLog(String username, String password) {
        this.mUsername = username;
        this.mPassword = password;
    }

    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String mUsername) {
        this.mUsername = mUsername;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String mPassword) {
        this.mPassword = mPassword;
    }

    @Override
    public String toString() {
        return "AccountLog{" +
                "mUsername='" + mUsername + '\'' +
                ", mPassword='" + mPassword + '\'' +
                '}' + '\n';
    }
}
