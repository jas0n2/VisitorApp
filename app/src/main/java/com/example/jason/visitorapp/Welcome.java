package com.example.jason.visitorapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.View;
import android.view.WindowManager;

import com.example.jason.visitorapp.util.Authhandler;

public class Welcome extends AppCompatActivity {
AppCompatButton signin,singout,Logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        signin = findViewById(R.id.signin);
        singout = findViewById(R.id.signout);
        Logout = findViewById(R.id.logout);


        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Authhandler.getAuthhandler(getApplicationContext()).logOut();
            }
        });


        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Welcome.this,SigninForm.class);
                startActivity(intent);

            }
        });
        singout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Welcome.this,Vistors.class);
                startActivity(intent);
            }
        });

    }
}
