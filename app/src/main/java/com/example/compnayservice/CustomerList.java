package com.example.compnayservice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CustomerList extends AppCompatActivity {

    public DataBaseHelperTest dataBaseHelperTest;

    SharedPreferences sharedPreferences;
    CompanyAdapter companyAdapter;
    GridView companyGridView;

    ArrayList<Company> companiesList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_list);


        sharedPreferences = getSharedPreferences(WelcomeScreen.MyPREFERENCES, Context.MODE_PRIVATE);
        //init database
        dataBaseHelperTest = new DataBaseHelperTest(CustomerList.this);
        showCompanies();

        TextView userEmail = findViewById(R.id.user_login);
        String name = sharedPreferences.getString(WelcomeScreen.Email, "user");
        userEmail.setText(name);

        final Button buttonLogoutUser = findViewById(R.id.button_user_logout);
        buttonLogoutUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logoutUser();
            }
        });

        companyAdapter = new CompanyAdapter(CustomerList.this, companiesList);
        companyGridView.setAdapter(companyAdapter);
    }


    /**
     * Get's all companies from database.
     */
    public void showCompanies(){
        //ArrayList<Company> companiesList = new ArrayList<>();
        companyGridView = findViewById(R.id.companies_view);
        try {
            companiesList = dataBaseHelperTest.getAllCompaniesForList();
        }
        catch (Exception e){
            Log.e("error database", e.getMessage());
        }
        if (companiesList.isEmpty()){
            Log.e("Database error", "data are not getting from databse");
            //companiesList.add(new Company(-1,"Test", "", "TestSi", "123", "test1", "Error"));
        }
        else{
            Log.e("Database error", "data are getting from database");
        }

     }


    /**
     * Logout user who is loged in.
     */
    public void logoutUser(){

         SharedPreferences.Editor editorUser = sharedPreferences.edit();
         editorUser.clear();
         editorUser.commit();
         finish();
     }





}