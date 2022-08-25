package com.example.renthouse;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class TenantDB extends SQLiteOpenHelper {

    public static final String dbname="Login.db";

    public TenantDB(Context context){
        super(context,dbname,null,3);
    }
    public TenantDB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "Login.db", null, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table users(email_address TEXT primary key not null, first_name TEXT not null, last_name TEXT not null, gender TEXT not null, password TEXT not null, Nationality TEXT not null,Gross_Monthly_Salary integer not null, Occupation TEXT not null,Family_Size integer not null, Current_residence_country TEXT not null,City TEXT not null, Phone_number TEXT not null)");
    }

    public Boolean insertUser(Tenant t) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("email_address",t.getEmailAddress());
        values.put("first_name", t.getFirstName());
        values.put("last_name", t.getLastName());
        values.put("gender",t.getGender());
        values.put("password", t.getPassword());
        values.put("Nationality",t.getNationality());
        values.put("Gross_Monthly_Salary",t.getGrossMonthlySalary());
        values.put("Occupation",t.getOccupation());
        values.put("Family_Size",t.getFamilySize());
        values.put("Current_residence_country",t.getCurrentResidenceCountry());
        values.put("City", t.getCity());
        values.put("Phone_number", t.getPhoneNumber());
        // Inserting Row
        long res= db.insert("users",null,values);
        if(res==-1)
            return false;
        else
            return true;
    }

    public Cursor getAllInformation(String email) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM users WHERE email_address =?",new String[]{email});
    }

    public Boolean checkEmailAddress(String ea){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE email_address =?",new String[]{ea});
        if (cursor.getCount() == 1)
            return true;
        else
            return false;
    }

    public Boolean checkPassword(String ea,String password){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE email_address =? and password=?",new String[]{ea,password});
        if (cursor.getCount() == 1)
            return true;
        else
            return false;
    }

    public boolean updateWithoutPassword(String email,String first_name,String last_name,String nationality,int Gross_Monthly_Salary,String occupation,int family_size ,String country,String city,String phone){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("first_name", first_name);
        contentValues.put("last_name", last_name);
        contentValues.put("Nationality", nationality);
        contentValues.put("Gross_Monthly_Salary", Gross_Monthly_Salary);
        contentValues.put("Occupation", occupation);
        contentValues.put("Family_Size", family_size);
        contentValues.put("Current_residence_country",country);
        contentValues.put("City", city);
        contentValues.put("Phone_number",phone);
        db.update("users", contentValues, "email_address = ? " ,new String[]{(email)});
        return true;

    }
    public boolean updateWithPassword(String email,String first_name,String last_name,String nationality,int Gross_Monthly_Salary,String occupation,int family_size,String country,String city,String password,String phone){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("first_name", first_name);
        contentValues.put("last_name", last_name);
        contentValues.put("Nationality", nationality);
        contentValues.put("Gross_Monthly_Salary", Gross_Monthly_Salary);
        contentValues.put("Occupation", occupation);
        contentValues.put("Family_Size", family_size);
        contentValues.put("Current_residence_country",country);
        contentValues.put("City", city);
        contentValues.put("password", password);
        contentValues.put("Phone_number",phone);
        db.update("users", contentValues, "email_address = ? " ,new String[]{(email)});
        return true;

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS users");
        this.onCreate(db);
    }
}
