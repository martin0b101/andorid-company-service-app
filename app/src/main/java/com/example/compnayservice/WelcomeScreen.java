package com.example.compnayservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WelcomeScreen extends AppCompatActivity {

    public static final String MyPREFERENCES = "MyPrefs";
    public static final String Email = "emailKey";

    public static final String CompanyPREFERENCES = "CompanyPref";
    public static final String CompanyEmail = "companyEmailKey";

    SharedPreferences sharedPreferences;
    SharedPreferences companySharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);

        sharedPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        companySharedPreferences = getSharedPreferences(CompanyPREFERENCES, Context.MODE_PRIVATE);

        final Button openReg = findViewById(R.id.button_register);
        openReg.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openRegister();
            }
        });

        final Button openLog = findViewById(R.id.button_login);
        openLog.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (sharedPreferences.contains(Email)){
                    openCompanyListScreen();
                }
                openLogIn();
            }
        });

        final Button openRegUser = findViewById(R.id.button_register_user);
        openRegUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRegNewUser();
            }
        });

        final Button openLogCompnay = findViewById(R.id.button_login_company);
        openLogCompnay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCompanyLoginScreen();
            }
        });
    }

    public void openRegister(){
        Intent newScreen = new Intent(this, RegisterScreen.class);
        startActivity(newScreen);
    }

    public void openLogIn(){
        Intent newScreen = new Intent(this, LoginScreen.class);
        startActivity(newScreen);
    }

    public void openRegNewUser(){
        Intent newScreen = new Intent(this, RegisterUser.class);
        startActivity(newScreen);
    }

    public void openCompanyLoginScreen(){
        Intent newScreen = new Intent(this, LoginCompany.class);
        startActivity(newScreen);
    }

    public void openCompanyListScreen(){
        Intent newScreen = new Intent(this, CustomerList.class);
        startActivity(newScreen);
    }



}