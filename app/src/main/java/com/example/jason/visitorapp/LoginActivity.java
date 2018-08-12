package com.example.jason.visitorapp;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.jason.visitorapp.Network.VolleySingleton;
import com.example.jason.visitorapp.modals.Auth;
import com.example.jason.visitorapp.util.Authhandler;
import com.example.jason.visitorapp.util.GlobalVariables;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

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
        if(Authhandler.getAuthhandler(getApplicationContext()).checkisLogin()){
            startActivity(new Intent(LoginActivity.this,Welcome.class));
        }
        setContentView(R.layout.activity_login);
        login = findViewById(R.id.layout);
        email = findViewById(R.id.userEmail);
        passoword = findViewById(R.id.userPassword);
        emailLayout = findViewById(R.id.userEmailL);
        passwordLayout = findViewById(R.id.userPaswordL);
        loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validateCredential()){
                    StringRequest request = new StringRequest(Request.Method.POST, GlobalVariables.authURL, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            //Toast.makeText(getApplicationContext(),String.valueOf(response),Toast.LENGTH_SHORT).show();

                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                String status = jsonObject.getString("status");
                                //Toast.makeText(getApplicationContext(),String.valueOf(status),Toast.LENGTH_SHORT).show();

                                JSONObject user = jsonObject.getJSONObject("user");
                                Toast.makeText(getApplicationContext(),String.valueOf(user),Toast.LENGTH_SHORT).show();


                                if(status.equals("login")){
                                    Authhandler.getAuthhandler(getApplicationContext()).logUser(new Auth(user.getString("id"),user.getString("name")));
                                    Intent intent = new Intent(LoginActivity.this,Welcome.class);
                                    startActivity(intent);
                                }else if(status.equals("failed")){
                                    Toast.makeText(getApplicationContext(),"Login Failed",Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //Toast.makeText(getApplicationContext(),String.valueOf(error),Toast.LENGTH_SHORT).show();

                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {

                            Map<String,String> map = new HashMap<>();
                            map.put("email",email.getText().toString().trim());
                            map.put("password",passoword.getText().toString().trim());
                            return map;
                        }
                    };
                    VolleySingleton.volleySingleton(getApplicationContext()).addToRequest(request);
                }
            }
        });

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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
