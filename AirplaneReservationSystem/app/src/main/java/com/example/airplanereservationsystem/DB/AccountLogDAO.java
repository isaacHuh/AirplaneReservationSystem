package com.example.airplanereservationsystem.DB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.airplanereservationsystem.AccountLog;

import java.util.List;

@Dao
public interface AccountLogDAO {
    @Insert
    void insert(AccountLog... accountlogs);

    @Update
    void update(AccountLog... accountlogs);

    @Delete
    void delete(AccountLog accountLog);

    @Query("SELECT * FROM " + AppDatabase.ACCOUNTLOG_TABLE + " ORDER BY mUsername DESC")
    List<AccountLog> getAllAccountLogs();

    @Query("SELECT * FROM " + AppDatabase.ACCOUNTLOG_TABLE + " WHERE mUsername = :username")
    AccountLog getAccountLogByUsername(String username);

    @Query("DELETE FROM " + AppDatabase.ACCOUNTLOG_TABLE)
    void deleteAll();
}
