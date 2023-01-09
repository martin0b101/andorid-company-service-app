package com.example.compnayservice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterUser extends AppCompatActivity {

    public DataBaseHelperTest dataBaseHelperTest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        //inti database
        dataBaseHelperTest = new DataBaseHelperTest(RegisterUser.this);


        final Button regNewUser = findViewById(R.id.button_register_new_user);
        regNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewUser();
            }
        });

    }


    public void createNewUser(){
        UserCustomer userCustomer = new UserCustomer(-1, getName(), getEmail(), getPassword());
        boolean newUserAdded = dataBaseHelperTest.addUser(userCustomer);

        Toast.makeText(this, "New user added="+newUserAdded, Toast.LENGTH_SHORT).show();
    }

    public String getName(){
        EditText name = findViewById(R.id.user_name);
        return name.getText().toString();
    }

    public String getEmail(){
        EditText email = findViewById(R.id.user_email);
        String emailAddress = email.getText().toString().trim();
        return emailAddress;
    }

    public String getPassword(){
        EditText pass = findViewById(R.id.user_pass);
        return pass.getText().toString();
    }
}