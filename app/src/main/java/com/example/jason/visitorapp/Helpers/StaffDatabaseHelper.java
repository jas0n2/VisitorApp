package com.example.jason.visitorapp.Helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.jason.visitorapp.util.GlobalVariables;

import static java.sql.Types.INTEGER;

public class StaffDatabaseHelper extends SQLiteOpenHelper {

    public StaffDatabaseHelper(Context context) {

        super(context, GlobalVariables.DATABASENAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.i("inset deb","created");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

}