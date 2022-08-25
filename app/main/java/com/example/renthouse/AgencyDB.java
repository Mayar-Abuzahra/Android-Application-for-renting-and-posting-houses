package com.example.renthouse;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import javax.xml.validation.Schema;

public class AgencyDB extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 3;
    private static final String DATABASE_NAME = "Schema6.db";


    public AgencyDB(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE Agency(Email TEXT PRIMARY KEY,Name TEXT,Password TEXT, Country TEXT,City TEXT,Phone long)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop Table if exists "+"Agency");

    }

    // insert  agency to the database
    public boolean insertAgency(Agency agency) {
        SQLiteDatabase sqLiteDatabase= getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Email", agency.getEmailAddress());
        contentValues.put("Name", agency.getAgencyName());
        contentValues.put("Password", agency.getPassword());
        contentValues.put("Country", agency.getCountry());
        contentValues.put("City", agency.getCity());
        contentValues.put("Phone", agency.getPhone());
        long res= sqLiteDatabase.insert("Agency",null,contentValues);
        if(res==-1)
            return false;
        else
            return true;
    }
    // to get the number of agencies
    public int getAllAgencies() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * FROM Agency", null);
        return cursor.getCount();
    }

    // to check the entry email
    public Boolean checkEmailAddress(String ea){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Agency WHERE Email =? ",new String[]{ea});
     //System.out.println("****************\n"+cursor.getCount());
        if (cursor.getCount()== 1)
            return true;
        else
            return false;
    }
    // to match the password with the entry password
    public Boolean checkPassword(String ea,String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Agency WHERE Email =? and Password=?",new String[]{ea,password});
        if (cursor.getCount()== 1)
            return true;
        else
            return false;
    }

    public Cursor getAllInformation(String email) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM Agency WHERE Email =? ",new String[]{email});
    }

public boolean updateWithoutPassword(String email,String name,String country,String city,long phone){
    SQLiteDatabase db = this.getWritableDatabase();
    ContentValues contentValues = new ContentValues();
    contentValues.put("Name", name);
    contentValues.put("Country",country);
    contentValues.put("City", city);
    contentValues.put("Phone",phone);
    db.update("Agency", contentValues, "Email = ? " ,new String[]{(email)});
    return true;

}
    public boolean updateWithPassword(String email,String name,String country,String city,String password,long phone){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Name", name);
        contentValues.put("Country",country);
        contentValues.put("City", city);
        contentValues.put("Password", password);
        contentValues.put("Phone",phone);
        db.update("Agency", contentValues, "Email = ? " ,new String[]{(email)});
        return true;

    }

}