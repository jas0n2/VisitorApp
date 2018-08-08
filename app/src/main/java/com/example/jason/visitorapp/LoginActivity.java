package com.example.jason.visitorapp;

import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;

public class LoginActivity extends AppCompatActivity  {
RelativeLayout login;
TextInputEditText email,passoword;
TextInputLayout emailLayout,passwordLayout;
Button loginButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        login = findViewById(R.id.layout);
        email = findViewById(R.id.userEmail);
        passoword = findViewById(R.id.userPassword);
        emailLayout = findViewById(R.id.userEmailL);
        passwordLayout = findViewById(R.id.userPaswordL);
        loginButton = findViewById(R.id.loginButton);

    }

    public  boolean  validateCredential(){
        if(email.getText().toString().isEmpty() || passoword.getText().toString().isEmpty()){
            emailLayout.setErrorEnabled(true);
            emailLayout.setError("please enter Your Email");
            passwordLayout.setError("Please enter Your Password");
            passwordLayout.setErrorEnabled(true);
            return  false;
        }else {
            emailLayout.setErrorEnabled(false);
            passwordLayout.setErrorEnabled(false);

          return  true;
        }


    }

    public void loginUser(){
        String emaila = email.getText().toString();
        String password = passoword.getText().toString();
    }
}
