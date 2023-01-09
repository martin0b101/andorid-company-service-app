package com.example.compnayservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginCompany extends AppCompatActivity {

    DataBaseHelperTest dataBaseHelperTest;
    SharedPreferences companySharedPrefrences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_company);

        //init
        dataBaseHelperTest = new DataBaseHelperTest(LoginCompany.this);
        companySharedPrefrences = getSharedPreferences(WelcomeScreen.CompanyPREFERENCES, Context.MODE_PRIVATE);

        final Button openMain = findViewById(R.id.button_login_comp);
        openMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginCompany(v);
            }
        });
    }

    /**
     * Login user if exist, and opens list of companies.
     * @param v
     */
    public void loginCompany(View v){
        boolean companyExist = dataBaseHelperTest.loginCompany(getEmail(), getPass());
        if (companyExist){
            //go to new view
            SharedPreferences.Editor editorUser = companySharedPrefrences.edit();
            editorUser.putString(WelcomeScreen.CompanyEmail, getEmail());
            editorUser.commit();
            openListOfCustomers(v);
            Toast.makeText(this, "Company exist!", Toast.LENGTH_SHORT).show();
        }
        else
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
    public void openListOfCustomers(View view){
        Intent newScreen = new Intent(this, MainScreen.class);
        startActivity(newScreen);
    }

}