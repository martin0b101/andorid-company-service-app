package com.example.compnayservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainScreen extends AppCompatActivity {

    DataBaseHelperTest dataBaseHelperTest;
    SharedPreferences companySharedPrefrences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);
        //init
        dataBaseHelperTest = new DataBaseHelperTest(MainScreen.this);
        companySharedPrefrences = getSharedPreferences(WelcomeScreen.CompanyPREFERENCES, Context.MODE_PRIVATE);

        String companyEmail = companySharedPrefrences.getString(WelcomeScreen.CompanyEmail, "Email Error");
        TextView emailComp = findViewById(R.id.customer_text);
        emailComp.setText(companyEmail);


        int getCompanyId = dataBaseHelperTest.getCompanyIdFromEmail(companyEmail);

        getCustomers(getCompanyId);

        final Button logoutButton = findViewById(R.id.button_company_logout);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logoutCompany();
            }
        });
    }

    private void getCustomers(int companyId){
        //get information from database
        GridView customerGridView = findViewById(R.id.customer_view);

        ArrayList<Customer> customerList = new ArrayList<Customer>();
        try {
            customerList = dataBaseHelperTest.getAllCustomers(companyId);
        }
        catch (Exception e){
            Log.e("error database", e.getMessage());
        }
        if (customerList.isEmpty()){
            Log.e("Database error", "data are not getting from databse");
        }
        else{
            Log.e("Database error", "data are getting from database");
        }

        CustomerAdapter adapter = new CustomerAdapter(this, customerList);
        customerGridView.setAdapter(adapter);

    }



    public void logoutCompany(){
        SharedPreferences sharedPreferences = getSharedPreferences(WelcomeScreen.MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editorUser = sharedPreferences.edit();
        editorUser.clear();
        editorUser.commit();
        finish();
    }

}