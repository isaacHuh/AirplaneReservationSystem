package com.example.airplanereservationsystem;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.airplanereservationsystem.DB.AccountLogDAO;
import com.example.airplanereservationsystem.DB.AppDatabase;
import com.example.airplanereservationsystem.DB.ReservationLogDAO;
import com.example.airplanereservationsystem.DB.TransactionLogDAO;

import java.util.HashSet;
import java.util.List;

public class CreateAccount extends AppCompatActivity {
    boolean valid = false;
    EditText mUsername, mPassword;
    Button mSubmit;

    AccountLogDAO mAccountLogDAO;
    List<AccountLog> mAccountLogs;

    TransactionLogDAO mTransactionLogDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        getSupportActionBar().setTitle("Create Account");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mUsername = findViewById(R.id.UsernameInput);
        mPassword = findViewById(R.id.PasswordInput);
        mSubmit = findViewById(R.id.ValidateUserButton);

        mAccountLogDAO = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DBNAME)
                .allowMainThreadQueries()
                .build()
                .getAccountLogDOA();

        mTransactionLogDAO = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DBNAME)
                .allowMainThreadQueries()
                .build()
                .getTransactionLogDOA();

        //mAccountLogDAO.deleteAll();
        mSubmit.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                //displayUserInfoTest();
                addToDataBase();
                //FlightDisplay();
                if(valid) {
                    openMainScreen();
                }
            }
        });

    }

    private void openMainScreen(){
        String username = mUsername.getText().toString();

        Intent intent = new Intent(this, MainScreen.class);
        MainScreen.curr_user = username;
        startActivity(intent);
    }

    public void addToDataBase(){
        String message = "Account Created.";
        String username = mUsername.getText().toString();
        String password = mPassword.getText().toString();

        AccountLog account = mAccountLogDAO.getAccountLogByUsername(username);

        if(account == null){
            if(validateInput(username) && validateInput(password) && !username.equals("admin2")) {
                AccountLog accountLog = new AccountLog(username, password);
                mAccountLogDAO.insert(accountLog);

                TransactionLog transactionLog = new TransactionLog("Create Account", username,"","","","",0,0,0.0);
                mTransactionLogDAO.insert(transactionLog);

                valid = true;
            }else{
                message = "Invalid Username/Password.";
            }
        }else{
            message = "Invalid Username. Account Already Exists.";
        }

        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    private boolean validateInput(String input){
        boolean numValid = false;
        HashSet<Character> letters = new HashSet<>();

        if(input.length() == 0){
            return false;
        }

        for(int i = 0; i < input.length(); i++){
            if(Character.isLetter(input.charAt(i))){
                letters.add(input.charAt(i));
                continue;
            }
            if(Character.isDigit(input.charAt(i))){
                numValid = true;
            }
        }

        if(numValid && letters.size() >= 3) {
            return true;
        }else{
            return false;
        }
    }
}
