package com.example.jason.visitorapp.util;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.jason.visitorapp.LoginActivity;
import com.example.jason.visitorapp.modals.Auth;

public class Authhandler {
    private static Authhandler authhandler;
    private static Context mcontext;


    public Authhandler(Context context) {
        this.mcontext =context;
    }

    public static synchronized Authhandler getAuthhandler(Context context){
        if(authhandler == null){
            authhandler = new Authhandler(context);
        }

        return  authhandler;
    }

    public  void logUser(Auth auth){
        SharedPreferences sharedPreferences = mcontext.getSharedPreferences("shared_login",Context.MODE_PRIVATE);
        SharedPreferences.Editor  editor = sharedPreferences.edit();

   editor.putString("id",auth.getId());
   editor.putString("name",auth.getName());

   editor.commit();
    }


    public void logOut(){
        SharedPreferences sharedPreferences = mcontext.getSharedPreferences("shared_login",Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = sharedPreferences.edit();

        ed.clear();
        ed.commit();
        Intent in = new Intent(mcontext, LoginActivity.class);
        mcontext.startActivity(in);
    }

    public boolean checkisLogin(){
     SharedPreferences sharedPreferences = mcontext.getSharedPreferences("shared_login",Context.MODE_PRIVATE);
     return  sharedPreferences.getString("name",null) != null;
    }



}
