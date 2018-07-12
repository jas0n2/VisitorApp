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
import android.util.Log;
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
    AppCompatSpinner reason,locationspinner;

    TextInputEditText name,email,phone,locationAdressh,locationAdresso,editTime;
    TextInputLayout officeLocation,houseLocation,nameLayout,
            emailLayout,phoneLayout,reasonLayout,stafflayout,locationTypeLayout,locationAddLayer,locationAddLayero,dateTime;
    AutoCompleteTextView staffName;
    ArrayList<Staff> staffArrayList ;
    AppCompatButton savedata;
    String  locationtypeSpinner ="";
    String  reasonSpinner ="";
    String staf_id = "";
    String staff_name = "";
    boolean validate =false;
    String  spinnerValidationtext ="";

    SQliteHelper sQliteHelper;

    ArrayAdapter<CharSequence> adapter,locationSpinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin_form);

        sQliteHelper = new SQliteHelper(getApplicationContext());

        singinLayout = findViewById(R.id.singinLayout);
        reason = findViewById(R.id.spinner);
        locationspinner = findViewById(R.id.spinnerlocation);
        savedata = findViewById(R.id.savedata);
        name = findViewById(R.id.editLayoutName);
        locationAdresso = findViewById(R.id.editLayoutHaddress);
        locationAdressh = findViewById(R.id.editOfficeAddress);

        phone = findViewById(R.id.editLayoutcontact);
        email = findViewById(R.id.editLayoutEmail);
        nameLayout = findViewById(R.id.textLayoutName);
        phoneLayout = findViewById(R.id.textLayoutNumber);
        emailLayout = findViewById(R.id.textLayoutEmail);
        locationAddLayer = findViewById(R.id.locationAddress);
        locationTypeLayout = findViewById(R.id.textLayoutLocation);
        reasonLayout = findViewById(R.id.textLayoutReason);
        // Validators.validateSpinner(reason,reasonLayout);
        dateTime = findViewById(R.id.textLayoutTime);
        editTime = findViewById(R.id.editTime);
        stafflayout = findViewById(R.id.textLayoutStaff);

        //validateData(name,email,phone,reason,staffName, locationspinner,locationAdressh,locationAdresso,nameLayout,emailLayout,phoneLayout,reasonLayout,stafflayout,locationTypeLayout,locationAddLayer);

        //getSupportActionBar().hide();
//populateStaff();
//        StaffDatabaseHelper helper = new StaffDatabaseHelper(getApplicationContext());
//        Cursor cursor = helper.getData();
//        staffArrayList = new ArrayList<>();
//        while (cursor.moveToNext()){
//            Staff staff = new Staff(cursor.getString(5),cursor.getString(1),cursor.getString(4));
//            staffArrayList.add(staff);
//        }

        staffName = findViewById(R.id.staffid);
//        StaffListAdapter sadapter = new StaffListAdapter(this,staffArrayList);
//        staffName.setAdapter(sadapter);
        locationSpinner = ArrayAdapter.createFromResource(getApplicationContext(),R.array.location,android.R.layout.simple_spinner_item);
        locationSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locationspinner.setAdapter(locationSpinner);
        officeLocation = findViewById(R.id.locationofAddress);
        houseLocation = findViewById(R.id.locationAddress);
      showLocationText(locationspinner,houseLocation,officeLocation);
        adapter = ArrayAdapter.createFromResource(this,R.array.reason,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        reason.setAdapter(adapter);
        savedata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean h= Util.checkConnection(getApplicationContext());
                Toast.makeText(getApplicationContext(),String.valueOf(h),Toast.LENGTH_SHORT).show();
                if(Util.checkConnection(getApplicationContext())){
                    validateData(name,email,phone,reason,staffName, locationspinner,locationAdressh,locationAdresso,nameLayout,emailLayout,phoneLayout,reasonLayout,stafflayout,locationTypeLayout,locationAddLayer,officeLocation);

                    insertData(name,email,phone,reason,staffName,
                            locationspinner,locationAdressh,locationAdresso,editTime,nameLayout,emailLayout,phoneLayout,reasonLayout,stafflayout,locationTypeLayout,locationAddLayer,officeLocation ,dateTime,1);
                }else {
                    validateData(name,email,phone,reason,staffName, locationspinner,locationAdressh,locationAdresso,nameLayout,emailLayout,phoneLayout,reasonLayout,stafflayout,locationTypeLayout,locationAddLayer,officeLocation);

                    insertData(name,email,phone,reason,staffName,
                           locationspinner,locationAdressh,locationAdresso,editTime,nameLayout,emailLayout,phoneLayout,reasonLayout,stafflayout,locationTypeLayout,locationAddLayer,officeLocation ,dateTime,1);
//                    Toast.makeText(getApplicationContext(),"Started",Toast.LENGTH_LONG).show();

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

    public  void insertData(
            TextInputEditText name,
            TextInputEditText email,
            TextInputEditText Phone,
            AppCompatSpinner reason,
            AutoCompleteTextView visitee,
            AppCompatSpinner locationtype,
            TextInputEditText locationaddressh,
            TextInputEditText locationaddresso,
            TextInputEditText dateTime,
            TextInputLayout namel,
            TextInputLayout emaill,
            TextInputLayout Phonel,
            TextInputLayout reasonl,
            TextInputLayout visiteel,
            TextInputLayout locationtypel,
            TextInputLayout locationaddresslh,
            TextInputLayout locationaddresslo,

            TextInputLayout dateTimel,
            int sync_status
    ) {



        if (
               !validateData(name,email,Phone,reason,visitee,locationtype,locationaddressh,locationaddresso,namel,emaill,Phonel,reasonl,visiteel,locationTypeLayout,locationaddresslh,locationaddresslo)
                ) {
         String editName = name.getText().toString();

            String editEmail = email.getText().toString();
            String editPhone = Phone.getText().toString();
            String editDate = dateTime.getText().toString();
            String editlocationAddress = locationaddresso.getText().toString();
            String editlocationAddressh = locationaddressh.getText().toString();
            String userlocation ="";
            locationtype.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    locationtypeSpinner = adapterView.getItemAtPosition(i).toString();

                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

            if(locationtypeSpinner.equals("Home")){
                userlocation = locationaddressh.getText().toString();

            }else if(locationtypeSpinner.equals("Company")){
                userlocation = locationaddresso.getText().toString();

            }
            reason.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    reasonSpinner = adapterView.getItemAtPosition(i).toString();

                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

            visitee.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Staff staff = (Staff) adapterView.getItemAtPosition(i);
                    staf_id = staff.getEmployeeId();
                    staff_name = staff.getEmployeeName();

                }
            });


            boolean saveData = sQliteHelper.addVistors(editName, editEmail, editPhone, reasonSpinner, staff_name, locationtypeSpinner, userlocation, staf_id, sync_status, editDate);

            if (saveData) {
                Toast.makeText(getApplicationContext(),"inserted",Toast.LENGTH_LONG).show();
            }

        }else {

        }

    }

    public  boolean validateData(
            TextInputEditText name,
            TextInputEditText email,
            TextInputEditText Phone,
            AppCompatSpinner reason,
            AutoCompleteTextView visitee,
            AppCompatSpinner locationtype,
            TextInputEditText locationaddressh,
            TextInputEditText locationaddresso,
            TextInputLayout namel,
            TextInputLayout emaill,
            TextInputLayout Phonel,
            TextInputLayout reasonl,
            TextInputLayout visiteel,
            TextInputLayout locationtypel,
            TextInputLayout locationaddresslo,
            TextInputLayout locationaddresslh


    ) {
        validateSpinner(reason, reasonl);
        Log.i("not spinner ",String.valueOf(validateSpinner(reason, reasonl)));
        Log.i("not spinner2 ",String.valueOf(validateSpinner(locationtype, locationTypeLayout)));

        Log.i("not empty2",String.valueOf(Validators.notEmpty(email, emaill)));
        Log.i("not empty3",String.valueOf(Validators.notEmpty(Phone, Phonel)));
        Log.i("not empty4",String.valueOf(Validators.notEmptyAddress(locationaddressh, locationaddresslh,locationaddresso,locationaddresslo,locationspinner)));
        Log.i("not empty6",String.valueOf(Validators.isEmail(emaill, email)));

        Log.i("not autocomplet",String.valueOf(Validators.notEmptyAutocomplete(visitee, visiteel)));

        if (
                !Validators.notEmpty(name, namel)
                        || !Validators.notEmpty(Phone, Phonel) ||
                        !Validators.notEmpty(email, emaill) ||
                        !Validators.isEmail(emaill,email)||
                        !Validators.notEmptyAddress(locationaddressh, locationaddresslh,locationaddresso,locationaddresslo,locationspinner) ||

                        !validateSpinner(reason, reasonl) ||
                        !Validators.validateSpinner(locationtype, locationtypel) ||
                        !Validators.notEmptyAutocomplete(visitee, visiteel)

                ) {


            return true;
        }else {

            return false;
        }

    }
    public     boolean validateSpinner(AppCompatSpinner spinner, TextInputLayout textInputLayout){

        if(spinner.getSelectedItem().toString().trim().equals("Select Reason") || spinner.getSelectedItem().toString().trim().equals("Select Location") ){
            textInputLayout.setErrorEnabled(true);
            textInputLayout.setError("Please Choose a reason");
            Log.i("not s0","d");

            return  false;

        }else{
            Log.i("not s","3");

            textInputLayout.setErrorEnabled(false);

            return  true;
        }
    }

    public     boolean validateSpinnerLocation(AppCompatSpinner spinner, TextInputLayout textInputLayout){

        Toast.makeText(getApplicationContext(),spinner.getSelectedItem().toString().trim(),Toast.LENGTH_LONG).show();
        if( spinner.getSelectedItem().toString().trim().equals("Select Location") ){
            textInputLayout.setErrorEnabled(true);
            textInputLayout.setError("Please Choose a reason");
            Log.i("not s0","d");

            return  false;

        }else{
            Log.i("not s","3");

            textInputLayout.setErrorEnabled(false);

            return  true;
        }
    }

    public  void showLocationText(AppCompatSpinner spinner, final TextInputLayout home, final TextInputLayout office){

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(adapterView.getItemAtPosition(i).toString().equals("Home")){
                    Log.i("home",adapterView.getItemAtPosition(i).toString());

                    home.setVisibility(View.VISIBLE);

                    office.setVisibility(View.GONE);
                }else if (adapterView.getItemAtPosition(i).toString().equals("Company")){
                    home.setVisibility(View.GONE);
                    office.setVisibility(View.VISIBLE);

                }else {
                    home.setVisibility(View.GONE);
                    office.setVisibility(View.GONE);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

}
