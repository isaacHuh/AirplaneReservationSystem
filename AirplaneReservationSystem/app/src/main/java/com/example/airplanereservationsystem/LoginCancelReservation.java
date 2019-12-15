package com.example.airplanereservationsystem;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.airplanereservationsystem.DB.AccountLogDAO;
import com.example.airplanereservationsystem.DB.AppDatabase;

public class LoginCancelReservation extends AppCompatActivity {

    Button loginBtn;
    int loginAttempts = 0;

    EditText username;
    EditText password;

    AccountLogDAO mAccountLogDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_cancel_reservation);

        getSupportActionBar().setTitle("Login");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        username = findViewById(R.id.CancelReservationUsername);
        password = findViewById(R.id.CancelReservationPassword);
        loginBtn = findViewById(R.id.CancelReservationButton);

        mAccountLogDAO = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DBNAME)
                .allowMainThreadQueries()
                .build()
                .getAccountLogDOA();

        //mAccountLogDAO.deleteAll();
        loginBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                login();
            }
        });
    }

    private void login(){
        String usernameString = username.getText().toString();
        String passwordString = password.getText().toString();

        AccountLog account = mAccountLogDAO.getAccountLogByUsername(usernameString);

        if(account != null){
            if(account.getPassword().equals(passwordString)) {
                CancelReservationDisplay.currUser = usernameString;
                Intent intent = new Intent(this, CancelReservationDisplay.class);
                startActivity(intent);
            }else{
                Toast.makeText(this, "Wrong password.", Toast.LENGTH_LONG).show();
            }
        }else{
            loginAttempts++;
            if(loginAttempts > 1){
                AlertDialog alert = invalid();
                alert.show();
            }
            Toast.makeText(this, "Wrong username.", Toast.LENGTH_LONG).show();
        }
    }

    private void returnToMain(){
        Intent intent = new Intent(this, MainScreen.class);
        startActivity(intent);
    }

    private AlertDialog invalid(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("You have entered too many invalid username/password inputs.")
                .setTitle("Too Many Invalid Inputs")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // CONFIRM
                        returnToMain();
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}
