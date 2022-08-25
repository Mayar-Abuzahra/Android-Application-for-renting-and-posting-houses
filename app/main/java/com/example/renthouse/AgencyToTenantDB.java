package com.example.renthouse;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class AgencyToTenantDB extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 5;
    private static final String DATABASE_NAME = "mayar.db";


    public AgencyToTenantDB(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    /*
    private int AgencyTOTenantID;
    private int houseID;
    private String city;
    private String postalAddress;
    private int rentingPeriod;
    private String agencyEmail;
    private String tenantEmail;
    private boolean waiting;
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE AgencytoTenant(AgencyTOTenantID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,houseId int,city TEXT ,postalAddress TEXT,rentingPeriod int, agencyEmail TEXT,tenantEmail Email,waiting boolean)");
    }

    public List<AgencyToTenant> history(String email){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM AgencytoTenant WHERE agencyEmail =? ",new String[]{email});
        List<AgencyToTenant> list=new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {

                int AgencyTOTenantID= Integer.parseInt(cursor.getString(0));
                int house_id = Integer.parseInt(cursor.getString(1));
                String city = cursor.getString(2);
                String postal_address= cursor.getString(3);
                int period = Integer.parseInt(cursor.getString(4));
                String agencyEmail = cursor.getString(5);
                String tenantEmail = cursor.getString(6);
                boolean waiting = Boolean.parseBoolean(cursor.getString(7));

                //(int house_id, String agencyEmail, String city, String postal_address, float surface_area, long construction_year, int number_of_bedrooms, float rental_price, boolean furnished, byte[] images, String availability_date, String description, boolean balcony, boolean garden)
              AgencyToTenant order=new  AgencyToTenant(AgencyTOTenantID,house_id,city,postal_address,period,agencyEmail,tenantEmail,waiting);
               list.add(order);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return list;
    }

    public List<AgencyToTenant> requests(String email){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM AgencytoTenant WHERE agencyEmail =? and waiting=? ",new String[]{email, String.valueOf(1)});
        List<AgencyToTenant> list=new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {

                int AgencyTOTenantID= Integer.parseInt(cursor.getString(0));
                int house_id = Integer.parseInt(cursor.getString(1));
                String city = cursor.getString(2);
                String postal_address= cursor.getString(3);
                int period = Integer.parseInt(cursor.getString(4));
                String agencyEmail = cursor.getString(5);
                String tenantEmail = cursor.getString(6);
                boolean waiting = Boolean.parseBoolean(cursor.getString(7));

                //(int house_id, String agencyEmail, String city, String postal_address, float surface_area, long construction_year, int number_of_bedrooms, float rental_price, boolean furnished, byte[] images, String availability_date, String description, boolean balcony, boolean garden)
                AgencyToTenant requests=new  AgencyToTenant(AgencyTOTenantID,house_id,city,postal_address,period,agencyEmail,tenantEmail,waiting);
                list.add(requests);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return list;
    }

    public boolean approveRequest(int AgencyTOTenantID){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("waiting",0);
        db.update("AgencyToTenant", contentValues, "AgencyTOTenantID = ? " ,new String[]{String.valueOf((AgencyTOTenantID))});
        return true;

    }
    public void deleteRequest(int AgencyTOTenantID){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("AgencyToTenant","AgencyTOTenantID = ? " ,new String[]{String.valueOf((AgencyTOTenantID))});
    }
    public boolean insertRequest(AgencyToTenant ag) {
        long res;
        SQLiteDatabase sqLiteDatabase1 =getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("houseId", ag.getHouseID());
        contentValues.put("city", ag.getCity());
        contentValues.put("postalAddress", ag.getPostalAddress());
        contentValues.put("rentingPeriod", ag.getRentingPeriod());
        contentValues.put("agencyEmail", ag.getAgencyEmail());
        contentValues.put("tenantEmail", ag.getTenantEmail());
        contentValues.put("waiting", ag.isWaiting());

        res = sqLiteDatabase1.insert("AgencytoTenant", null, contentValues);

        if(res==-1)
            return false;
        else
            return true;
    }

    public Cursor getNeededTenantInfo(String str) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery(str, null);
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
