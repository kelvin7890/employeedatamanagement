package com.example.employeemanagementapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText username,password,repassword;
    Button Signup,SignIn;
    DBHandler DB;
@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        Signup=findViewById(R.id.Signup);
        SignIn=findViewById(R.id.SignIn);

        DB= new DBHandler(this);

        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              String admin= username.getText().toString();
              String pass= password.getText().toString();
              String repass= repassword.getText().toString();

              if(TextUtils.isEmpty(admin) || TextUtils.isEmpty(pass) || TextUtils.isEmpty(repass))
                  Toast.makeText(MainActivity.this, "All fields are Required!!", Toast.LENGTH_SHORT).show();
              else {
                  if(pass.equals(repass)){
                      Boolean checkadmin = DB.checkusername(admin);
                      if(checkadmin==false){
                          Boolean Insert = DB.InsertData(admin, pass);
                          if(Insert==true){
                              Toast.makeText(MainActivity.this,"Registration was succesful", Toast.LENGTH_SHORT).show();
                              Intent intent = new Intent(getApplicationContext(),Home.class);
                              startActivity(intent);
                          } else {
                              Toast.makeText(MainActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                          }

                      }else {
                          Toast.makeText(MainActivity.this, "Admin already Registered",Toast.LENGTH_SHORT).show();
                      }
                  }else {
                      Toast.makeText(MainActivity.this, "Passwords do not much, please confirm your password",Toast.LENGTH_SHORT).show();
                  }
              }
            }
        });
        SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Login.class);
                startActivity(intent);
            }
        });
    }
}