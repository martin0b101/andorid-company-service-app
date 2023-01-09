package com.example.compnayservice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

public class RegisterScreen extends AppCompatActivity {

    private FirebaseAuth mAuth;

    public DataBaseHelperTest dataBaseHelperTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen);

        // inti database
        dataBaseHelperTest = new DataBaseHelperTest(RegisterScreen.this);

        //mAuth = FirebaseAuth.getInstance();

        Boolean equalPass = false;
        String[] countries = {"SI - Slovenia", "HR - Croatia", "DE - Germany", "IT - Italy"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, countries);
        AutoCompleteTextView textView = (AutoCompleteTextView)
                findViewById(R.id.dorp_down_country);
        textView.setAdapter(adapter);

        final Button buttonNext = findViewById(R.id.button_next);
        buttonNext.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                signInNewCompany(v);

            }
        });
    }

    /**
     * Adds new user company to databse.
     */
    public void signInNewCompany(View v){
        Company newCompany = new Company(-1,companyName(), VATnumber(), getCountry(), getPassword(), getEmail(), getWork());

        boolean success = dataBaseHelperTest.addNewCompany(newCompany);

        if (success)
            mainScreen(v);
    }

    /**
     * Opens main screen activity.
     * @param view
     */
    public void mainScreen(View view){
        Intent intent = new Intent(this, MainScreen.class);
        startActivity(intent);
    }

    private String getWork() {
        EditText work = findViewById(R.id.editWorkType);
        return work.getText().toString();
    }

    private String getPassword(){
        EditText psw1 = findViewById(R.id.editTextTextPassword);

        String pass1 = psw1.getText().toString();
        if (pass1.isEmpty()){
            psw1.setError("Password is required!");
        }

        if (pass1.length() < 8){
            psw1.setError("Minimal password length should be 8 characters!");
        }

        return pass1;
    }

    private String getEmail(){
        EditText email = findViewById(R.id.editTextTextEmailAddress);
        String emailAddress = email.getText().toString().trim();
        if (emailAddress.isEmpty()){
            email.setError("Email address is required!");
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(emailAddress).matches()){
            email.setError("Please provide valid email!");
        }

        return emailAddress;
    }

    private String companyName(){
        EditText compN = findViewById(R.id.editTextTextPersonName);
        String companyName = compN.getText().toString().trim();
        if (companyName.isEmpty()){
            compN.setError("Company Name is required!");
        }
        return companyName;
    }

    private String VATnumber() {

        EditText vatN = findViewById(R.id.editTextTextPersonName2);
        String vatNumber = vatN.getText().toString().trim();
        if (vatNumber.isEmpty()){
            vatN.setError("VAT number is required!");
        }
        if (!(vatNumberChecker(vatNumber, getCountry()))){
            vatN.setError("VAT number is not valid!");
        }

        return vatNumber;

    }

    private String getCountry(){
        /*
        String country = spinner.getSelectedItem().toString();
        if (country.equals("Select country")){
            ((TextView)spinner.getSelectedView()).setError("Please select country");
        }
        String[] reslut = country.split(" - ");

         */

        return "SI";
    }

/*
    private void dropDownCountries(){
        Spinner spinner = findViewById(R.id.dorp_down_country);
        String[] countries = {"Select country", "SI - Slovenia", "HR - Croatia", "DE - Germany", "IT - Italy"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, countries);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);
    }*/

    private boolean vatNumberChecker(String vatNumber, String countryCode){
        String regex = getCountryVATformat(countryCode);
        String[] validVATlenght = vatNumber.split(regex);
        if (validVATlenght.length == 0){
            return true;
        }
        return false;
    }

    private String getCountryVATformat(String MemberState){
        String[] countryCode = {"SI", "DE", "IT", "AT", "HR"};
        String[] regexCountry = {"[0-9]{8}", "[0-9]{9}", "[0-9]{11}", "U[0-9]{8}", "[0-9]{11}"};
        for (int i = 0; i < countryCode.length; i++) {
            if (MemberState.equals(countryCode[i])){
                return regexCountry[i];
            }
        }
        return "";

    }



}