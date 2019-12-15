package com.example.airplanereservationsystem;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginManageSystem extends AppCompatActivity {

    Button loginBtn;
    int loginAttempts = 0;

    EditText username;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_manage_system);

        getSupportActionBar().setTitle("Login");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        loginBtn = findViewById(R.id.ManageSytemLoginButton);
        username = findViewById(R.id.ManageSystemLoginUsername);
        password = findViewById(R.id.ManageSystemLoginPassword);

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

        if(usernameString.equals("admin2")){
            if(passwordString.equals("admin2")){
                Intent intent = new Intent(this, ManageSystem.class);
                startActivity(intent);
            }
        }else{
            loginAttempts++;
            if(loginAttempts > 1){
                AlertDialog alert = invalid();
                alert.show();
            }
            Toast.makeText(this, "Wrong inputs.", Toast.LENGTH_LONG).show();
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
