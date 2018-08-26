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
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS "+ GlobalVariables.TABLENAME1 +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,"+GlobalVariables.COL22+" TEXT,"+GlobalVariables.COL33+" TEXT,"+GlobalVariables.COL44+","+GlobalVariables.COL55+" TEXT ,"+GlobalVariables.COL60+" TEXT)");
  sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS "+GlobalVariables.TABLENAME2 +"(ID INTEGER PRIMARY KEY AUTOINCREMENT, auto INTEGER) ");
  sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS "+GlobalVariables.TABLENAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,"+GlobalVariables.COL2+" TEXT," +
                ""+GlobalVariables.COL3+" TEXT,"+GlobalVariables.COL4+" NUMERIC,"+GlobalVariables.COL5+" NUMERIC,"+GlobalVariables.COL6+" TEXT,"+GlobalVariables.COL7+" TEXT,"+GlobalVariables.COL8+ " TEXT,"+GlobalVariables.COL9+" TEXT,"+GlobalVariables.COL10+" INTEGER,"+GlobalVariables.COL12+" INTEGER,"+GlobalVariables.COL13+" TEXT,"+GlobalVariables.COL14+" TEXT,"+GlobalVariables.COL15+" TEXT,"+GlobalVariables.COL16+" INTEGER,"+GlobalVariables.COL17+" INTEGER )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+GlobalVariables.TABLENAME1);

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+GlobalVariables.TABLENAME);
    }
    public boolean addVistors(String name,String email,String Phone,String reason,String visitee,String locationtype,String locationaddress,String staff_id,int sync_satus,String dateTime,String timein,String timeout,String status,long id){
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
        values.put(GlobalVariables.COL13,timein);
        values.put(GlobalVariables.COL14,timeout);
        values.put(GlobalVariables.COL15,status);
        values.put(GlobalVariables.COL16,id);
        values.put(GlobalVariables.COL17,0);


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
        Cursor cursor = database.rawQuery("Select * from "+GlobalVariables.TABLENAME+" where status =? " ,new String[]{"in"});

        return cursor;
    }


    public long  updateOnSync(int id,int sync){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(GlobalVariables.COL10,sync);
        long result =  db.update(GlobalVariables.TABLENAME,values,"id=?",new String[]{String.valueOf(id)});

        return result;
    }

    public long  bgUpdateOnSync(int id ,int sync){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(GlobalVariables.COL17,sync);
        long result =  db.update(GlobalVariables.TABLENAME,values,"id=?",new String[]{String.valueOf(id)});

        return result;
    }


    public Cursor getNotSync(){
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery("Select * from "+GlobalVariables.TABLENAME+" where sync_status =? " ,new String[]{"0"});
        return cursor;
    }
    //this is to get signout on the
    public Cursor getUpdateNotSync(){
        SQLiteDatabase database = getReadableDatabase();


        Cursor cursor = database.rawQuery("Select * from "+GlobalVariables.TABLENAME+" where update_sync =? " ,new String[]{"0"});


        return cursor;
    }

    public boolean populateTable(String name,String email,String phonenumber,String department,String staff_id){
        ContentValues contentValues = new ContentValues();
        contentValues.put(GlobalVariables.COL22 ,name);
        contentValues.put(GlobalVariables.COL33,email);
        contentValues.put(GlobalVariables.COL44,phonenumber);
        contentValues.put(GlobalVariables.COL55,department);
        contentValues.put(GlobalVariables.COL60,staff_id);

        SQLiteDatabase sqLiteDatabase  = this.getWritableDatabase();
        long h = sqLiteDatabase.insert(GlobalVariables.TABLENAME1,null,contentValues);
        if(h == -1){
            return false;
        }else {
            return true ;
        }
    }
    public  long generateID(){
        int count = 0;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
         values.put("auto",count++);
        long id = sqLiteDatabase.insert(GlobalVariables.TABLENAME2,null,values);

        return  id;
    }

    public boolean signoutQuery(int id,int update_sync,String timeout){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(GlobalVariables.COL15,"out");
        values.put(GlobalVariables.COL17,update_sync);
        values.put(GlobalVariables.COL14,timeout);
        long update  = database.update(GlobalVariables.TABLENAME,values,"id=?",new String[]{String.valueOf(id)});
        if(update == -1){
            return false;
        }else {
            return true;
        }

      //database.rawQuery("UPDATE "+GlobalVariables.TABLENAME1+" SET status=? WHERE ID=? ",new String[]{"out",String.valueOf(id)});

    }

    public Cursor getData(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor =  sqLiteDatabase.rawQuery("Select * from "+GlobalVariables.TABLENAME1+"" ,null);
        return cursor;
    }

}
