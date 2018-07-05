package com.example.jason.visitorapp.Helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.jason.visitorapp.util.GlobalVariables;

public class SQliteHelper extends SQLiteOpenHelper{



    public SQliteHelper(Context context) {
        super(context, GlobalVariables.DATABASENAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS "+GlobalVariables.TABLENAME +" (ID PRIMARY KEY AUTOINCREMENT,"+GlobalVariables.COL2+" TEXT," +
                ""+GlobalVariables.COL3+" TEXT,"+GlobalVariables.COL4+" NUMERIC,"+GlobalVariables.COL5+" NUMERIC,"+GlobalVariables.COL6+" TEXT,"+GlobalVariables.COL7+" TEXT,"+GlobalVariables.COL8+ " TEXT,"+GlobalVariables.COL9+" TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+GlobalVariables.TABLENAME);
    }
}
