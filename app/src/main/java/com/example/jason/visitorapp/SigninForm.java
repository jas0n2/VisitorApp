package com.example.jason.visitorapp;

import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSpinner;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.jason.visitorapp.util.Util;

public class SigninForm extends AppCompatActivity {
RelativeLayout singinLayout;
AppCompatSpinner spinner,locationspinner;

TextInputEditText name,email,phone,reason;
TextInputLayout officeLocation,houseLocation;

ArrayAdapter<CharSequence> adapter,locationSpinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin_form);
        singinLayout = findViewById(R.id.singinLayout);
        spinner = findViewById(R.id.spinner);
        locationspinner = findViewById(R.id.spinnerlocation);
        //getSupportActionBar().hide();
        locationSpinner = ArrayAdapter.createFromResource(getApplicationContext(),R.array.location,android.R.layout.simple_spinner_item);
        locationSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locationspinner.setAdapter(locationSpinner);
        officeLocation = findViewById(R.id.textLayoutOfficeAddress);
        houseLocation = findViewById(R.id.textLayoutHaddress);



        Util.getUtil(getApplicationContext()).showLocationText(locationspinner,houseLocation,officeLocation);
        adapter = ArrayAdapter.createFromResource(this,R.array.reason,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);








        singinLayout.setOnClickListener(null);
    }


}
