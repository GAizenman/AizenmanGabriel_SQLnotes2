package com.example.aizenmang2856.mycontactapp_2018;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DatabaseHelper extends SQLiteOpenHelper {


    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Contact2018-2.db";
    public static final String TABLE_NAME = "Contact2018_table";
    public static final String ID = "ID";
    public static final String COLUMN_NAME_CONTACT = "contact";
    public static final String COLUMN_NUMBER_CONTACT = "phoneNumber";
    public static final String COLUMN_ADDRESS_CONTACT = "address";

    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NAME_CONTACT + " TEXT, " + COLUMN_NUMBER_CONTACT + " NUMBER, " + COLUMN_ADDRESS_CONTACT + " TEXT)";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d("MyContactApp", "DatabaseHelper: constructed DatabaseHelper");
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("MyContactApp", "DatabaseHelper: creating database");
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("MyContactApp", "DatabaseHelper: upgrading DatabaseHelper");
        db.execSQL(SQL_DELETE_ENTRIES);
    }

    public boolean insertData(String name, String num, String address){
        Log.d("MyContactApp", "DatabaseHelper: inserting data");
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME_CONTACT, name);
        contentValues.put(COLUMN_NUMBER_CONTACT, num);
        contentValues.put(COLUMN_ADDRESS_CONTACT, address);


        long result = db.insert(TABLE_NAME, null, contentValues);

        if (result == -1){
            Log.d("MyContactApp", "DatabaseHelper: Contact insert - FAILED");
            return false;
        }
        else{
            Log.d("MyContactApp", "DatabaseHelper: Contact insert - PASSED");
            return true;
        }


    }

    public Cursor getAllData(){
        Log.d("MyContactApp", "MDatabaseHelper: pulling all data from db");
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }


}