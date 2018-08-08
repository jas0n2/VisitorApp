package com.example.jason.visitorapp.util;

import android.content.Context;
import android.content.SharedPreferences;

public class Authhandler {
    private static Authhandler authhandler;
    private static Context mcontext;


    public Authhandler(Context context) {
        this.mcontext =context;
    }

    public synchronized Authhandler getAuthhandler(Context context){
        if(authhandler == null){
            authhandler = new Authhandler(context);
        }

        return  authhandler;
    }

    public  void logUser(Authhandler authhandler){
        SharedPreferences sharedPreferences = mcontext.getSharedPreferences("shared_login",Context.MODE_PRIVATE);
        SharedPreferences.Editor  editor = sharedPreferences.edit();

        //editor.putString("id",authhandler.ge)
    }




}
