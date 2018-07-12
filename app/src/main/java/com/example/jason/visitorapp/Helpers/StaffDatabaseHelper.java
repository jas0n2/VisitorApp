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

        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS "+GlobalVariables.TABLENAME1 +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,"+GlobalVariables.COL22+" TEXT,"+GlobalVariables.COL33+" TEXT,"+GlobalVariables.COL44+","+GlobalVariables.COL55+" TEXT ,"+GlobalVariables.COL60+" TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+GlobalVariables.TABLENAME1);
    }
    public void populateTable(String name,String email,String phonenumber,String department,String staff_id){
        ContentValues contentValues = new ContentValues();
        contentValues.put(GlobalVariables.COL22 ,name);
        contentValues.put(GlobalVariables.COL33,email);
        contentValues.put(GlobalVariables.COL44,phonenumber);
        contentValues.put(GlobalVariables.COL55,department);
        contentValues.put(GlobalVariables.COL60,staff_id);

        SQLiteDatabase sqLiteDatabase  = this.getWritableDatabase();
        long h = sqLiteDatabase.insert(GlobalVariables.TABLENAME1,null,contentValues);
      //  Log.i("h",String.valueOf(h));
        }

        public Cursor getData(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
      Cursor cursor =  sqLiteDatabase.rawQuery("Select * from "+GlobalVariables.TABLENAME1,null);
      return cursor;
        }
}
