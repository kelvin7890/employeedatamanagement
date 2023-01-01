package com.example.employeemanagementapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    EditText username, password;
    Button SignIn;
    DBHandler DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username=findViewById(R.id.username1);
        password=findViewById(R.id.password1);
        SignIn=findViewById(R.id.SignIn);

        DB= new DBHandler(this);

        SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String admin=username.getText().toString();
                String pass=password.getText().toString();

                if(TextUtils.isEmpty(admin) || TextUtils.isEmpty(pass))
                    Toast.makeText(Login.this, "All fields are Required!!", Toast.LENGTH_SHORT).show();
                else {
                    Boolean checkpass = DB.checkusernamepassword(admin, pass);
                    if (checkpass == true) {

                        Toast.makeText(Login.this, "Login was succesful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), Home.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(Login.this, "Login failed", Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });


    }
}