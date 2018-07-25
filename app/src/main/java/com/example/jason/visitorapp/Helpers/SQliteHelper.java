package com.example.jason.visitorapp.Helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.jason.visitorapp.util.GlobalVariables;

import static java.sql.Types.INTEGER;

public class SQliteHelper extends SQLiteOpenHelper{



    public SQliteHelper(Context context) {
        super(context, GlobalVariables.DATABASENAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS "+GlobalVariables.TABLENAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,"+GlobalVariables.COL2+" TEXT," +
                ""+GlobalVariables.COL3+" TEXT,"+GlobalVariables.COL4+" NUMERIC,"+GlobalVariables.COL5+" NUMERIC,"+GlobalVariables.COL6+" TEXT,"+GlobalVariables.COL7+" TEXT,"+GlobalVariables.COL8+ " TEXT,"+GlobalVariables.COL9+" TEXT,"+GlobalVariables.COL10+" INTEGER,"+GlobalVariables.COL12+" INTEGER,"+GlobalVariables.COL13+" TEXT,"+ GlobalVariables.COL14 +"TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+GlobalVariables.TABLENAME);
    }
    public boolean addVistors(String name,String email,String Phone,String reason,String visitee,String locationtype,String locationaddress,String staff_id,int sync_satus,String dateTime){
        ContentValues values = new ContentValues();
        values.put(GlobalVariables.COL2,name);
        values.put(GlobalVariables.COL3,email);
        values.put(GlobalVariables.COL4,Phone);
        values.put(GlobalVariables.COL5,dateTime);
        values.put(GlobalVariables.COL6,reason);
        values.put(GlobalVariables.COL7,visitee);
        values.put(GlobalVariables.COL8,locationtype);
        values.put(GlobalVariables.COL9,locationaddress);
        values.put(GlobalVariables.COL10,sync_satus);
        values.put(GlobalVariables.COL12,staff_id);
        values.put(GlobalVariables.COL13,dateTime);
        values.put(GlobalVariables.COL14,dateTime);

        SQLiteDatabase database = this.getWritableDatabase();
        long insert =  database.insert(GlobalVariables.TABLENAME,null,values);

        Log.i("inserted",String.valueOf(insert));
        if(insert != -1)
            return true;
        else
            return false;

   }

   public Cursor getVisitors(){
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery("Select * from "+GlobalVariables.TABLENAME,null);

        return cursor;
   }

}
