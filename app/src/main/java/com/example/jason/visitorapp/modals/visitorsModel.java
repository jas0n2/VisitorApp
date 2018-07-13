package com.example.jason.visitorapp.modals;

import android.content.Context;

import java.util.ArrayList;

public class visitorsModel {
    private visitorsModel visitorsModel;

    private Context context;
    ArrayList<Visitors> visitorsArrayList;

    public visitorsModel(Context context) {
        this.context = context;
        visitorsArrayList = new ArrayList<>();
    }

    public visitorsModel getVisitorsModel(Context context){
        if(visitorsModel == null){
            visitorsModel = new visitorsModel(context);
        }

        return visitorsModel;
    }

    public  ArrayList<Visitors> getVisitorsList(){
        return visitorsArrayList;
    }
}
