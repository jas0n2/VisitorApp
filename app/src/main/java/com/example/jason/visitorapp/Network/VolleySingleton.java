package com.example.jason.visitorapp.Network;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class VolleySingleton {
    private static VolleySingleton mInstance;
    private  Context mContext;
    private RequestQueue requestQueue;

    public VolleySingleton(Context mContext) {
        this.mContext = mContext;
        requestQueue = getRequestQueue();
    }

    public static synchronized VolleySingleton volleySingleton(Context context){
        if(mInstance == null){
            mInstance = new VolleySingleton(context);
        }
        return mInstance;
    }

    private RequestQueue getRequestQueue(){
        if(requestQueue == null){
            requestQueue = Volley.newRequestQueue(mContext.getApplicationContext());
        }
        return requestQueue;
    }

    public<T> void addToRequest(Request<T> request){
         getRequestQueue().add(request);
    }
}
