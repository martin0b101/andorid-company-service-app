package com.example.compnayservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class LoginScreen extends AppCompatActivity {


    DataBaseHelperTest dataBaseHelperTest;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        //init
        dataBaseHelperTest = new DataBaseHelperTest(LoginScreen.this);

        sharedPreferences = getSharedPreferences(WelcomeScreen.MyPREFERENCES, Context.MODE_PRIVATE);

        Button button_login = findViewById(R.id.button_login);
        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser(v);
            }
        });
    }

    /**
     * Login user if exist, and opens list of companies.
     * @param v
     */
    public void loginUser(View v){
        String userEmail = getEmail();
        boolean userExist = dataBaseHelperTest.loginUser(userEmail, getPass());
        if (userExist){
            //save logged in user
            SharedPreferences.Editor editorUser = sharedPreferences.edit();
            editorUser.putString(WelcomeScreen.Email, userEmail);
            editorUser.commit();
            //open new
            openListOfCompanies(v);
            Toast.makeText(this, "User exist!", Toast.LENGTH_SHORT).show();
        }
        Toast.makeText(this, "Prosim, da se registrirate!", Toast.LENGTH_SHORT).show();
    }

    public String getEmail(){
        EditText email = findViewById(R.id.email_address_login);
        return email.getText().toString();
    }

    public String getPass(){
        EditText pass = findViewById(R.id.password_login);
        return pass.getText().toString();
    }

    public void openListOfCompanies(View view){
        Intent newScreen = new Intent(this, CustomerList.class);
        startActivity(newScreen);
    }




}