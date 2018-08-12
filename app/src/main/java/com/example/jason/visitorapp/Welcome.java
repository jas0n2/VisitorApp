package com.example.jason.visitorapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.jason.visitorapp.Helpers.SQliteHelper;
import com.example.jason.visitorapp.Network.VolleySingleton;
import com.example.jason.visitorapp.modals.Staffs;
import com.example.jason.visitorapp.util.Authhandler;
import com.example.jason.visitorapp.util.GlobalVariables;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Welcome extends AppCompatActivity {
    AppCompatButton signin,singout,Logout;
    Staffs staffs;
    JSONObject jsonObject;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        getSupportActionBar().hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        signin = findViewById(R.id.signin);
        singout = findViewById(R.id.signout);
        Logout = findViewById(R.id.logout);
        final SQliteHelper helper = new SQliteHelper(getApplicationContext());
        Cursor cursor = helper.getVisitors();
        int cout = cursor.getCount();
        sharedPreferences = this.getSharedPreferences("imsert",MODE_PRIVATE);

String isStaff  = sharedPreferences.getString("notemptystaff",null);
        Toast.makeText(getApplicationContext(),String.valueOf(isStaff),Toast.LENGTH_LONG).show();

     if(isStaff == null) {




            JsonObjectRequest objectRequest = new JsonObjectRequest(GlobalVariables.staffSync, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                   // Log.i("text",String.valueOf(response));
                  //Toast.makeText(getApplicationContext(),String.valueOf(response),Toast.LENGTH_LONG).show();


                    try {
                        JSONArray jsonObject = response.getJSONArray("staff");

                        //Toast.makeText(getApplicationContext(),String.valueOf(jsonObject.g),Toast.LENGTH_LONG).show();



                        for (int i=0;i<jsonObject.length();i++) {
                            JSONObject onObject = jsonObject.optJSONObject(i);
                            Toast.makeText(getApplicationContext(), String.valueOf(onObject.get("fname")), Toast.LENGTH_LONG).show();

                            boolean st =helper.populateTable(onObject.getString("fname"), onObject.getString("email"), onObject.getString("phonenumber"), onObject.getString("department"), onObject.getString("id"));

                        if(st){

                            SharedPreferences.Editor editor = sharedPreferences.edit();
                             editor.putString("notemptystaff","1");
                             editor.commit();

                        }
                        }
                        //Toast.makeText(getApplicationContext(),String.valueOf(jsonArray.get("fname")),Toast.LENGTH_LONG).show();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });


            VolleySingleton.volleySingleton(getApplicationContext()).addToRequest(objectRequest);



        }



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
