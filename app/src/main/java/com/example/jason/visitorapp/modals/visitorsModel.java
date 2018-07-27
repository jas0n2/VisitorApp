package com.example.jason.visitorapp.modals;

import android.content.Context;
import android.database.Cursor;

import com.example.jason.visitorapp.Helpers.SQliteHelper;
import com.example.jason.visitorapp.Vistors;

import java.util.ArrayList;

public class visitorsModel {
    private static visitorsModel visitorsModel;

    private Context context;
    ArrayList<Visitors> visitorsArrayList;

    public interface OnAddListener{
        void onadd(ArrayList<Visitors> vistors);
    }


    private OnAddListener onAddListener;

    public OnAddListener getOnAddListener() {
        return onAddListener;
    }

    public void setOnAddListener(OnAddListener onAddListener) {
        this.onAddListener = onAddListener;
    }

    public visitorsModel(Context context) {
        this.context = context;
        visitorsArrayList = new ArrayList<>();

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

    public void addVisitor(Visitors vistors){
        visitorsArrayList.add(vistors);
    }

    public  void addData(Visitors visitors){
        visitorsArrayList.add(visitors);
        //onaddStudent.addstudent();

    }
public void removeVistors(){
         visitorsArrayList.clear();

}
}
