package com.example.jason.visitorapp;

import android.database.Cursor;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatSpinner;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.jason.visitorapp.Adapters.StaffListAdapter;
import com.example.jason.visitorapp.Helpers.SQliteHelper;
import com.example.jason.visitorapp.Helpers.StaffDatabaseHelper;
import com.example.jason.visitorapp.modals.Staff;
import com.example.jason.visitorapp.util.Util;
import com.example.jason.visitorapp.util.Validators;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class SigninForm extends AppCompatActivity {
RelativeLayout singinLayout;
AppCompatSpinner spinner,locationspinner;

TextInputEditText name,email,phone,reason;
TextInputLayout officeLocation,houseLocation;
AutoCompleteTextView staffName;
ArrayList<Staff> staffArrayList ;
AppCompatButton savedata;

ArrayAdapter<CharSequence> adapter,locationSpinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin_form);
        singinLayout = findViewById(R.id.singinLayout);
        spinner = findViewById(R.id.spinner);
        locationspinner = findViewById(R.id.spinnerlocation);
        savedata = findViewById(R.id.savedata);
        //getSupportActionBar().hide();
//populateStaff();
        StaffDatabaseHelper helper = new StaffDatabaseHelper(getApplicationContext());
        Cursor cursor = helper.getData();
        staffArrayList = new ArrayList<>();
        while (cursor.moveToNext()){
            Staff staff = new Staff(cursor.getString(5),cursor.getString(1),cursor.getString(4));
            staffArrayList.add(staff);
        }


        staffName = findViewById(R.id.staffid);
        StaffListAdapter sadapter = new StaffListAdapter(this,staffArrayList);
        staffName.setAdapter(sadapter);
        locationSpinner = ArrayAdapter.createFromResource(getApplicationContext(),R.array.location,android.R.layout.simple_spinner_item);
        locationSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locationspinner.setAdapter(locationSpinner);
        officeLocation = findViewById(R.id.textLayoutOfficeAddress);
        houseLocation = findViewById(R.id.textLayoutHaddress);
        Util.getUtil(getApplicationContext()).showLocationText(locationspinner,houseLocation,officeLocation);
        adapter = ArrayAdapter.createFromResource(this,R.array.reason,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        savedata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Util.checkConnection(getApplicationContext())){

                }else {

                }


            }
        });








        singinLayout.setOnClickListener(null);
    }
    public void populateStaff(){
        StaffDatabaseHelper databaseHelper = new StaffDatabaseHelper(getApplicationContext());
        databaseHelper.populateTable("Tefutor ceaser","ceaserjayson@gmail.com","024191","IT","1");
        databaseHelper.populateTable("Joshua Lawson","ceaserjayson@gmail.com","024191","IT","2");

        databaseHelper.populateTable("Dela Asigbetse","ceaserjayson@gmail.com","024191","Premium Admin","3");
        databaseHelper.populateTable("Jason jayson","ceaserjayson@gmail.com","024191","HR","4");


    }

    public  void insertData(TextInputEditText name,TextInputEditText email,TextInputEditText Phone,TextInputEditText reason,String visitee,String locationtype,TextInputEditText locationaddress,int staff_id,int sync_satus){
        SQliteHelper sQliteHelper = new SQliteHelper(getApplicationContext());
        if(Validators.notEmpty(name.))


        if(sQliteHelper.addVistors(name.getText().toString(), email, Phone, reason, visitee, locationtype, locationaddress, staff_id, sync_satus)){
            finish();
        }
    }

}
