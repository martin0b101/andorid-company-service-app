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

public class OrderServices extends AppCompatActivity {

    public SharedPreferences sharedPreferences;
    public DataBaseHelperTest dataBaseHelperTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_services);

        sharedPreferences = getSharedPreferences(WelcomeScreen.MyPREFERENCES, Context.MODE_PRIVATE);
        dataBaseHelperTest = new DataBaseHelperTest(OrderServices.this);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();


        EditText companyName = findViewById(R.id.company_name_selected);
        String companyNameString = (String) bundle.get("companyName");
        int companyId = (Integer) bundle.get("companyId");
        companyName.setText(companyNameString);

        Button orderToCompany = findViewById(R.id.button_customer_add);
        orderToCompany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewOrderToDatabase(companyId);
            }
        });
    }

    public void addNewOrderToDatabase(int companyId){
        int customerUserId = dataBaseHelperTest.getUserIdFromEmail(getUserEmail());
        Customer newCustomer = new Customer(customerUserId, getFullName(), getUserEmail(), getWork(), getTime(), companyId);
        if(dataBaseHelperTest.addNewCustomerToCompany(newCustomer)){
            Intent backToCompanyListScreen = new Intent(this, CustomerList.class);
            startActivity(backToCompanyListScreen);
        }
    }

    public String getFullName(){
        EditText fullName = findViewById(R.id.full_name_input);
        String fullNameString = fullName.getText().toString().trim();
        return fullNameString;
    }

    public String getWork(){
        EditText work = findViewById(R.id.work_type);
        String workString = work.getText().toString().trim();
        return workString;
    }
    public String getTime(){
        EditText time = findViewById(R.id.time);
        String timeString = time.getText().toString().trim();
        return timeString;
    }
    public String getUserEmail(){
        String userEmail = sharedPreferences.getString(WelcomeScreen.Email, "user");
        return userEmail;
    }

}