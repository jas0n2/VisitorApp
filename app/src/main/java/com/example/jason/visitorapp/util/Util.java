package com.example.jason.visitorapp.util;

import android.content.Context;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatSpinner;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

public class Util  {
    private Context context;
    private static Util util;

    public  Util(Context context){
        this.context = context;
    }

    public static Util getUtil(Context context){
        if(util == null){
            util = new Util(context);
        }

        return util;
    }


    public  void showLocationText(AppCompatSpinner spinner, final TextInputLayout home, final TextInputLayout office){

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(adapterView.getItemAtPosition(i).toString().equals("Home")){
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
