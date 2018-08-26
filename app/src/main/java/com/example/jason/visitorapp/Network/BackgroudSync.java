package com.example.jason.visitorapp.Network;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.database.Cursor;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.jason.visitorapp.Helpers.SQliteHelper;
import com.example.jason.visitorapp.modals.Visitors;
import com.example.jason.visitorapp.util.GlobalVariables;
import com.google.gson.internal.bind.SqlDateTypeAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class BackgroudSync extends JobService {

    private static String TAG ="SYNC";
    private boolean jobCancel = false;
   // long id
    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        Log.d(TAG,"Started");
        SyncData(jobParameters);
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        Log.d(TAG,"Stopped");

        jobCancel = true;
        return true;
    }

    public  void SyncData(final JobParameters parameters){
        final SQliteHelper sQliteHelper = new SQliteHelper(getApplicationContext());
        final Cursor cursor = sQliteHelper.getNotSync();

        while (cursor.moveToNext()){
            final  String id = cursor.getString(0);
            Log.d(TAG,String.valueOf(id));
            final String name =cursor.getString(cursor.getColumnIndex(GlobalVariables.COL2));
            Log.d(TAG,String.valueOf(name));

            final String email =cursor.getString(cursor.getColumnIndex(GlobalVariables.COL3));
            final String phone =cursor.getString(cursor.getColumnIndex(GlobalVariables.COL4));
            final String reason =cursor.getString(cursor.getColumnIndex(GlobalVariables.COL6));
            final String staffname =cursor.getString(cursor.getColumnIndex(GlobalVariables.COL7));
            final String loctionType =cursor.getString(cursor.getColumnIndex(GlobalVariables.COL8));
            final String locationAdress =cursor.getString(cursor.getColumnIndex(GlobalVariables.COL9));
            final String staf_id =cursor.getString(cursor.getColumnIndex(GlobalVariables.COL12));
            final String status =cursor.getString(cursor.getColumnIndex(GlobalVariables.COL15));
            final String sync_status =cursor.getString(cursor.getColumnIndex(GlobalVariables.COL10));



            final String date =cursor.getString(cursor.getColumnIndex(GlobalVariables.COL5));
            final String timein =cursor.getString(cursor.getColumnIndex(GlobalVariables.COL13));
            final String timeout =cursor.getString(cursor.getColumnIndex(GlobalVariables.COL14));
            final String visitor_id =cursor.getString(cursor.getColumnIndex(GlobalVariables.COL16));


                    StringRequest stringRequest = new StringRequest(Request.Method.POST, GlobalVariables.serverURL, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        String status = jsonObject.getString("status");
                        if(status.equals("ok")){
                           //id =sQliteHelper.generateID();
                          long result = sQliteHelper.updateOnSync(Integer.valueOf(id),1);

                          Log.d(TAG,String.valueOf(result));
                    //    sQliteHelper.addVistors(name, email, phone, reason, staffname, loctionType, locationAdress, staf_id, Integer.valueOf(sync_status), date,timein,timeout,status,id);
                           Log.d(TAG,"response");
                            if(jobCancel){
                                return;
                            }
                       jobFinished(parameters,true);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    sQliteHelper.updateOnSync(Integer.valueOf(id),0);

                    jobFinished(parameters,true);

                }
            })
            {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> map = new HashMap<>();
                    map.put("name",name);
                    map.put("email",email);
                    map.put("phone",phone);
                    map.put("reason",reason);
                    map.put("staffname",staffname);
                    map.put("loctionType",loctionType);
                    map.put("locationAdress",locationAdress);
                    map.put("staf_id",staf_id);
                    map.put("date",date);
                    map.put("timein",timein);
                    map.put("timeout",timeout);
                    map.put("visitor_id",visitor_id);
                    map.put("status",status);
                    return map;
                }
            };
            VolleySingleton.volleySingleton(getApplicationContext()).addToRequest(stringRequest);
            jobFinished(parameters,false);



        }



        }


}
