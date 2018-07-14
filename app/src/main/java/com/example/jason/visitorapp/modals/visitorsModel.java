package com.example.jason.visitorapp.modals;

import android.content.Context;
import android.database.Cursor;

import com.example.jason.visitorapp.Helpers.SQliteHelper;

import java.util.ArrayList;

public class visitorsModel {
    private static visitorsModel visitorsModel;

    private Context context;
    ArrayList<Visitors> visitorsArrayList;

    public visitorsModel(Context context) {
        this.context = context;
        visitorsArrayList = new ArrayList<>();
        SQliteHelper helper = new SQliteHelper(context);
        Cursor cursor = helper.getVisitors();
        while (cursor.moveToNext()){
            Visitors visitors = new Visitors(cursor.getString(0),cursor.getString(0),cursor.getString(0),cursor.getString(0),cursor.getString(0),cursor.getString(0),cursor.getString(0),cursor.getString(0));
            visitorsArrayList.add(visitors);
        }
    }

    public static visitorsModel getVisitorsModel(Context context){
        if(visitorsModel == null){
            visitorsModel = new visitorsModel(context);
        }

        return visitorsModel;
    }

    public  ArrayList<Visitors> getVisitorsList(){
        return visitorsArrayList;
    }
}
