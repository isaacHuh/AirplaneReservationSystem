package com.example.airplanereservationsystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

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

import java.util.HashSet;
import java.util.List;

public class CreateAccount extends AppCompatActivity {
    boolean valid = false;
    EditText mUsername, mPassword;
    Button mSubmit;

    AccountLogDAO mAccountLogDAO;
    List<AccountLog> mAccountLogs;

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
    private void displayUserInfoTest(){
        Log.i("Username: ", mUsername.getText().toString());
        Log.i("Password: ", mPassword.getText().toString());
        /*
        if(account.getPassword().equals(password)){
            Log.i("addToDataBase: ", "password matches\n");
        }else{
            Log.i("addToDataBase: ", "password does not match\n");
        }*/
    }
    private void test(){
        mAccountLogs = mAccountLogDAO.getAllAccountLogs();

        if(!mAccountLogs.isEmpty()){
            StringBuilder stringBuilder = new StringBuilder();

            for(AccountLog accountLog : mAccountLogs){
                stringBuilder.append(accountLog.toString());
            }

            Log.i("account:",stringBuilder.toString());
        }else{
            Log.i("account:","none");
        }

    }
}
