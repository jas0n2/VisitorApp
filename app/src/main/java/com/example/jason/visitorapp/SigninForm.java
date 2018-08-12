package com.example.jason.visitorapp;

import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.AppCompatTextView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.jason.visitorapp.Adapters.StaffListAdapter;
import com.example.jason.visitorapp.Helpers.SQliteHelper;
import com.example.jason.visitorapp.Helpers.StaffDatabaseHelper;
import com.example.jason.visitorapp.Network.VolleySingleton;
import com.example.jason.visitorapp.modals.Staff;
import com.example.jason.visitorapp.modals.Visitors;
import com.example.jason.visitorapp.modals.visitorsModel;
import com.example.jason.visitorapp.util.GlobalVariables;
import com.example.jason.visitorapp.util.Util;
import com.example.jason.visitorapp.util.Validators;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SigninForm extends AppCompatActivity {
    RelativeLayout singinLayout;
    AppCompatSpinner reason,locationspinner;

    TextInputEditText name,email,phone,locationAdressh,locationAdresso;
    AppCompatTextView editTime;
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

    SQliteHelper databaseHelper;
    Map<String,String> map;
    ArrayAdapter<CharSequence> adapter,locationSpinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin_form);
        //StaffDatabaseHelper helper = new StaffDatabaseHelper(getApplicationContext());

        databaseHelper = new SQliteHelper(getApplicationContext());

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
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-mm-yy  HH:mm:ss");
        editTime.setText(simpleDateFormat.format(calendar.getTime()));


        //validateData(name,email,phone,reason,staffName, locationspinner,locationAdressh,locationAdresso,nameLayout,emailLayout,phoneLayout,reasonLayout,stafflayout,locationTypeLayout,locationAddLayer);

        //getSupportActionBar().hide();

        //populateStaff();
        Cursor cursor = databaseHelper.getData();
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
        officeLocation = findViewById(R.id.locationofAddress);
        houseLocation = findViewById(R.id.locationAddress);
        showLocationText(locationspinner,houseLocation,officeLocation);
        staffName.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Staff staff = (Staff) adapterView.getItemAtPosition(i);
                staf_id = staff.getEmployeeId();
                staff_name = staff.getEmployeeName();


            }
        });
        adapter = ArrayAdapter.createFromResource(this,R.array.reason,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        reason.setAdapter(adapter);
        savedata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                VolleyLog.DEBUG = true;
                populateHashMap(name,email,phone,reason,staffName,
                        locationspinner,locationAdressh,locationAdresso,editTime,nameLayout,emailLayout,phoneLayout,reasonLayout,stafflayout,locationTypeLayout,locationAddLayer,officeLocation ,dateTime,1);

                //Toast.makeText(getApplicationContext(),String.valueOf(validateData(name,email,phone,reason,staffName, locationspinner,locationAdressh,locationAdresso,nameLayout,emailLayout,phoneLayout,reasonLayout,stafflayout,locationTypeLayout,locationAddLayer,officeLocation)),Toast.LENGTH_SHORT).show();
//                boolean h= Util.checkConnection(getApplicationContext());
//                Toast.makeText(getApplicationContext(),String.valueOf(h),Toast.LENGTH_SHORT).show();

                if(Util.checkConnection(getApplicationContext())) {

                    boolean validate = validateData(name, email, phone, reason, staffName, locationspinner, locationAdressh, locationAdresso, nameLayout, emailLayout, phoneLayout, reasonLayout, stafflayout, locationTypeLayout, locationAddLayer, officeLocation);

                    if (validate) {

                        StringRequest request = new StringRequest(Request.Method.POST, GlobalVariables.serverURL, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();
                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    String result = jsonObject.getString("status");
                                    if (result.equals("ok")) {
                                        visitorsModel.getVisitorsModel(getApplicationContext()).clearViitor();
                                        visitorsModel.getVisitorsModel(getApplicationContext()).allVisitors();
                                        insertData(name, email, phone, reason, staffName,
                                                locationspinner, locationAdressh, locationAdresso, editTime, nameLayout, emailLayout, phoneLayout, reasonLayout, stafflayout, locationTypeLayout, locationAddLayer, officeLocation, dateTime, 1);
                                        Intent in = new Intent(SigninForm.this, Welcome.class);
                                        startActivity(in);

                                    } else {
                                        visitorsModel.getVisitorsModel(getApplicationContext()).clearViitor();
                                        visitorsModel.getVisitorsModel(getApplicationContext()).allVisitors();
                                        insertData(name, email, phone, reason, staffName,
                                                locationspinner, locationAdressh, locationAdresso, editTime, nameLayout, emailLayout, phoneLayout, reasonLayout, stafflayout, locationTypeLayout, locationAddLayer, officeLocation, dateTime, 0);

                                        Intent in = new Intent(SigninForm.this, Welcome.class);
                                        startActivity(in);
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // Log.d("Volley",error.getMessage());
                                Toast.makeText(getApplicationContext(), String.valueOf(error.getMessage()), Toast.LENGTH_SHORT).show();

                            }


                        }) {


                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {

//                           Map<String,String> map = new HashMap<>();
//                            map.put("name","S");
//                            map.put("email","S");
//                            map.put("phone","022");
//                            map.put("reason","sld");
//                            map.put("staffname","DS");
//                            map.put("loctionType","Sd");
//                            map.put("locationAdress","sd");
//                            map.put("staf_id","1");
//                            map.put("date","2018-10-2");
//                            map.put("timein","12:20");
//                            map.put("timeout","12:20");

                                //map.put("Content-Type", "application/json");
                                return map;
                            }

                        };


                        request.setShouldCache(false);
                        request.setRetryPolicy(new DefaultRetryPolicy(0, -1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                        VolleySingleton.volleySingleton(SigninForm.this).addToRequest(request);

                    } else {
//                    validateData(name,email,phone,reason,staffName, locationspinner,locationAdressh,locationAdresso,nameLayout,emailLayout,phoneLayout,reasonLayout,stafflayout,locationTypeLayout,locationAddLayer,officeLocation);

                        if (insertData(name, email, phone, reason, staffName,
                                locationspinner, locationAdressh, locationAdresso, editTime, nameLayout, emailLayout, phoneLayout, reasonLayout, stafflayout, locationTypeLayout, locationAddLayer, officeLocation, dateTime, 1)) {
                            visitorsModel.getVisitorsModel(getApplicationContext()).clearViitor();
                            visitorsModel.getVisitorsModel(getApplicationContext()).allVisitors();

                            Intent in = new Intent(SigninForm.this, Welcome.class);
                            startActivity(in);

                        } else {
                            return;
                        }
                        //                    Toast.makeText(getApplicationContext(),"Started",Toast.LENGTH_LONG).show();

                    }

                }else {
                    return;
                }
            }
        });
        singinLayout.setOnClickListener(null);
    }
    public void populateStaff(){
        databaseHelper.populateTable("Tefutor ceaser","ceaserjayson@gmail.com","024191","IT","1");
        databaseHelper.populateTable("Joshua Lawson","ceaserjayson@gmail.com","024191","IT","2");

        databaseHelper.populateTable("Dela Asigbetse","ceaserjayson@gmail.com","024191","Premium Admin","3");
        databaseHelper.populateTable("Jason jayson","ceaserjayson@gmail.com","024191","HR","4");


    }

    public  boolean insertData(
            TextInputEditText name,
            TextInputEditText email,
            TextInputEditText Phone,
            final AppCompatSpinner reason,
            AutoCompleteTextView visitee,
            AppCompatSpinner locationtype,
            TextInputEditText locationaddressh,
            TextInputEditText locationaddresso,
            AppCompatTextView dateTime,
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
                validateData(name,email,Phone,reason,visitee,locationtype,locationaddressh,locationaddresso,namel,emaill,Phonel,reasonl,visiteel,locationTypeLayout,locationaddresslh,locationaddresslo)
                ) {
            String editName = name.getText().toString();

            String editEmail = email.getText().toString();
            String editPhone = Phone.getText().toString();
            String editDate = dateTime.getText().toString();
            String userlocation ="";
            locationtypeSpinner =locationtype.getSelectedItem().toString().trim();
            if(locationtypeSpinner.equals("Home")){
                userlocation = locationaddresslh.getEditText().getText().toString();


            }else if(locationtypeSpinner.equals("Company")){
                userlocation =locationaddresslo.getEditText().getText().toString();

            }
            reasonSpinner = reason.getSelectedItem().toString().trim();








            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-dd-mm");
            String date = simpleDateFormat.format(calendar.getTime());
            Calendar calendar2 = Calendar.getInstance();
            SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("HH:mm:ss");
            String timein = simpleDateFormat2.format(calendar2.getTime());

            boolean saveData = databaseHelper.addVistors(editName, editEmail, editPhone, reasonSpinner, staff_name, locationtypeSpinner, userlocation, staf_id, sync_status, date,timein,"","in");

            map = new HashMap();
            map.put("name",editName);
            map.put("email",editEmail);
            map.put("phone",editPhone);
            map.put("reason",reasonSpinner);
            map.put("staffname",staff_name);
            map.put("loctionType",locationtypeSpinner);
            map.put("locationAdress",userlocation);
            map.put("staf_id",staf_id);
            map.put("date",date);
            map.put("timein",timein);
            map.put("timeout","");
            map.put("status","in");

            // map.put("name",name);
            return true;

        }else {
            return false;
        }

    }



    public  boolean populateHashMap(
            TextInputEditText name,
            TextInputEditText email,
            TextInputEditText Phone,
            final AppCompatSpinner reason,
            AutoCompleteTextView visitee,
            AppCompatSpinner locationtype,
            TextInputEditText locationaddressh,
            TextInputEditText locationaddresso,
            AppCompatTextView dateTime,
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
                validateData(name,email,Phone,reason,visitee,locationtype,locationaddressh,locationaddresso,namel,emaill,Phonel,reasonl,visiteel,locationTypeLayout,locationaddresslh,locationaddresslo)
                ) {
            String editName = name.getText().toString();

            String editEmail = email.getText().toString();
            String editPhone = Phone.getText().toString();
            String editDate = dateTime.getText().toString();
            String userlocation ="";
            locationtypeSpinner =locationtype.getSelectedItem().toString().trim();
            if(locationtypeSpinner.equals("Home")){
                userlocation = locationaddresslh.getEditText().getText().toString();


            }else if(locationtypeSpinner.equals("Company")){
                userlocation =locationaddresslo.getEditText().getText().toString();

            }
            reasonSpinner = reason.getSelectedItem().toString().trim();








            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String date = simpleDateFormat.format(calendar.getTime());
            Calendar calendar2 = Calendar.getInstance();
            SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("HH:mm:ss");
            String timein = simpleDateFormat2.format(calendar2.getTime());


            map = new HashMap();
            map.put("name",editName);
            map.put("email",editEmail);
            map.put("phone",editPhone);
            map.put("reason",reasonSpinner);
            map.put("staffname",staff_name);
            map.put("loctionType",locationtypeSpinner);
            map.put("locationAdress",userlocation);
            map.put("staf_id",staf_id);
            map.put("date",date);
            map.put("timein",timein);
            map.put("timeout","");
            // map.put("name",name);
            return true;

        }else {
            return false;
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


        if (
                Validators.notEmpty(name, namel)
                        && Validators.notEmpty(Phone, Phonel) &&
                        Validators.notEmpty(email, emaill) &&
                        Validators.isEmail(emaill,email)&&
                        Validators.notEmptyAddress(locationaddressh, locationaddresslh,locationaddresso,locationaddresslo,locationspinner) &&
                        validateSpinner(reason, reasonl) &&
                        Validators.validateSpinner(locationtype, locationtypel) &&
                        Validators.notEmptyAutocomplete(visitee, visiteel)
                        && Validators.validateStaff(staf_id,stafflayout)

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

            return  false;

        }else{


            textInputLayout.setErrorEnabled(false);

            return  true;
        }
    }



    public  void showLocationText(AppCompatSpinner spinner, final TextInputLayout home, final TextInputLayout office){

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(adapterView.getItemAtPosition(i).toString().equals("Home")){
                    home.setVisibility(View.VISIBLE);
                    home.requestFocus();


                    office.setVisibility(View.GONE);
                }else if (adapterView.getItemAtPosition(i).toString().equals("Company")){
                    home.setVisibility(View.GONE);
                    office.setVisibility(View.VISIBLE);
                    office.requestFocus();

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
