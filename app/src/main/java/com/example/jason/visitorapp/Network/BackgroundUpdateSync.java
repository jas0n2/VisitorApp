package com.example.jason.visitorapp.Network;

import android.app.job.JobParameters;
import android.app.job.JobService;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.jason.visitorapp.Helpers.SQliteHelper;
import android.database.Cursor;
import android.util.Log;
import android.widget.Toast;

import com.example.jason.visitorapp.util.GlobalVariables;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class BackgroundUpdateSync extends JobService {
    private static  String TAG = "UPADTE_SYNC";
    private boolean cancelSync  =false;

    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        Log.d(TAG,"Started");
        Sync(jobParameters);
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        cancelSync =true;
        return true;
    }


    public  void  Sync(final JobParameters parameters){
        final SQliteHelper helper = new SQliteHelper(getApplicationContext());
        Cursor cursor = helper.getUpdateNotSync();

        while (cursor.moveToNext()){
            final  String vid = cursor.getString(0);
            final String id = cursor.getString(cursor.getColumnIndex(GlobalVariables.COL16));
            final String timeout = cursor.getString(cursor.getColumnIndex(GlobalVariables.COL14));
            String  syn = cursor.getString(cursor.getColumnIndex(GlobalVariables.COL17));
            String bgSync = cursor.getString(cursor.getColumnIndex(GlobalVariables.COL10));
            //Log.d(TAG,String.valueOf(id));

            if(bgSync.equals("1") && !timeout.equals("") && syn.equals("0")){

               // Log.d(TAG,String.valueOf(timeout));

                JSONObject jsonObject = new JSONObject();


//                try {
//                    jsonObject.put("id",id);
//                    jsonObject.put("timeout",timeout);
//
//                    JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.POST,GlobalVariables.udateStatus, jsonObject, new Response.Listener<JSONObject>() {
//                        @Override
//                        public void onResponse(JSONObject response) {
//
//                        }
//                    }, new Response.ErrorListener() {
//                        @Override
//                        public void onErrorResponse(VolleyError error) {
//
//                        }
//                    });
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//

                StringRequest request = new StringRequest(Request.Method.POST, GlobalVariables.udateStatus, new Response.Listener<String>() {


                    @Override
                    public void onResponse(String response) {
                  Log.d(TAG,response);
                        try {
                            JSONObject object = new JSONObject(response);
                            String status = object.getString("status");
                            if(status.equals("ok")){
                                helper.bgUpdateOnSync(Integer.valueOf(vid),1);
                                jobFinished(parameters,false);
                                Log.d(TAG,"response");
                                if(cancelSync){
                                    return;
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        jobFinished(parameters,false);


                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> map = new HashMap<>();
                        map.put("id",id);
                        map.put("timeout",timeout);
                        return map;
                    }
                };
                VolleySingleton.volleySingleton(getApplicationContext()).addToRequest(request);

            }
        }
    }
}
