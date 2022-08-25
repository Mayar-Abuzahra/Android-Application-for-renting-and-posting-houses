package com.example.renthouse;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class HouseDB extends SQLiteOpenHelper {

/*
    private int house_id;
    private String agencyEmail;
    private String city;
    private String postal_address;
    private float surface_area;
    private long construction_year;
    private int number_of_bedrooms;
    private float rental_price;
    private boolean furnished;
    private byte[] images;
    private Date availability_date;
    private String description;
    private boolean balcony;
    private boolean garden;

 */
private static final int DATABASE_VERSION = 4;
private static final String DATABASE_NAME = "raneem.db";

    public HouseDB(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);


    }

    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE House(house_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,agencyEmail TEXT,city TEXT,postal_address TEXT ,surface_area float, construction_year long,number_of_bedrooms int,rental_price float,furnished boolean,images blob,availability_date TEXT,description TEXT,balcony boolean,garden boolean)");

    }

    public boolean insertHouse(House house) {
        long res;
       SQLiteDatabase sqLiteDatabase1 =getWritableDatabase();
       System.out.println("*******************"+house);
            ContentValues contentValues = new ContentValues();
            contentValues.put("agencyEmail", house.getAgencyEmail());
            contentValues.put("city", house.getCity().toLowerCase());
            contentValues.put("postal_address", house.getPostal_address());
            contentValues.put("surface_area", house.getSurface_area());
            contentValues.put("number_of_bedrooms", house.getNumber_of_bedrooms());
            contentValues.put("construction_year", house.getConstruction_year());
            contentValues.put("rental_price", house.getRental_price());
            contentValues.put("furnished", house.isFurnished());
            contentValues.put("images", house.getImages());
            contentValues.put("availability_date", house.getAvailability_date());
            contentValues.put("description", house.getDescription());
            contentValues.put("balcony", house.isBalcony());
            contentValues.put("garden", house.isGarden());

            res = sqLiteDatabase1.insert("House", null, contentValues);

        if(res==-1)
            return false;
        else
            return true;
    }
    public Cursor getAllHouses() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT * FROM House", null);
    }
    /*


     private int house_id;
    private String agencyEmail;
    private String city;
    private String postal_address;
    private float surface_area;
    private long construction_year;
    private int number_of_bedrooms;
    private float rental_price;
    private boolean furnished;
    private byte[] images;
    private String availability_date;
    private String description;
    private boolean balcony;
    private boolean garden;

     */

    public List<House> posts(String email){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM House WHERE agencyEmail =? ",new String[]{email});
        List<House> list=new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {

                System.out.println("****************************************");
                int house_id = Integer.parseInt(cursor.getString(0));
                String agencyEmail = cursor.getString(1);
                String city = cursor.getString(2);
                String postal_address= cursor.getString(3);
                float surface_area = cursor.getFloat(4);
                long costruction_year = (cursor.getLong(5));
                int number_of_bedrooms = (cursor.getInt(6));
                System.out.println("**************************"+number_of_bedrooms);
                float rental_price = (cursor.getFloat(7));
                boolean furnished =cursor.getInt(8)== 1 ? true:false;
                byte [] images =cursor.getBlob(9);
                //System.out.println(images);
                String availability_date = cursor.getString(10);
                String description = cursor.getString(11);
                boolean balcony =cursor.getInt(12)== 1 ? true:false;
                boolean garden = cursor.getInt(13)== 1 ? true:false;
                //(int house_id, String agencyEmail, String city, String postal_address, float surface_area, long construction_year, int number_of_bedrooms, float rental_price, boolean furnished, byte[] images, String availability_date, String description, boolean balcony, boolean garden)
                House post=new House(house_id,agencyEmail,city,postal_address,surface_area,costruction_year,number_of_bedrooms,rental_price,furnished, images,availability_date,description,balcony,garden);
                list.add(post);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        //System.out.println(list.toString());
        return list;
    }

    public boolean updatePost(int houseID,String city,String postalAddress,float area,long year,int room,float price,String date,String describtion,boolean furnished,boolean garden,boolean balcony){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("city", city);
        contentValues.put("postal_address", postalAddress);
        contentValues.put("surface_area",area);
        contentValues.put("construction_year",year);
        contentValues.put("number_of_bedrooms", room);
        contentValues.put("rental_price", price);
        contentValues.put("description",describtion);
        contentValues.put("availability_date", date);
        contentValues.put("furnished ",furnished);
        contentValues.put("balcony",  balcony);
        contentValues.put("garden", garden);

        db.update("House", contentValues, "house_id = ? " ,new String[]{String.valueOf((houseID))});
        return true;

    }
    public void deletePost(int houseID){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("House","house_id = ? " ,new String[]{String.valueOf((houseID))});
     }
    public Cursor getNeededHouses(String str) {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery(str, null);
    }
    public Cursor getSortedHouses() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        return sqLiteDatabase.rawQuery("SELECT *\n" +
                "FROM\n" +
                "    ( SELECT house_id, agencyEmail,city,postal_address,surface_area,construction_year,number_of_bedrooms,rental_price,furnished, DATE(availability_date) AS updated_date, availability_date,description,balcony,garden\n" +
                "      FROM House\n" +
                "      ORDER BY availability_date DESC\n" +
                "      LIMIT 5\n" +
                "    ) AS tmp\n" +
                "ORDER BY availability_date", null);
    }

    public List<House> GetNew(){
        SQLiteDatabase db = this.getWritableDatabase();
        String select_query = "select * from " + "House";
        Cursor cursor = db.rawQuery(select_query, null);
        List<House> list=new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {

                System.out.println("****************************************");
                int house_id = Integer.parseInt(cursor.getString(0));
                String agencyEmail = cursor.getString(1);
                String city = cursor.getString(2);
                String postal_address= cursor.getString(3);
                float surface_area = cursor.getFloat(4);
                long costruction_year = (cursor.getLong(5));
                int number_of_bedrooms = (cursor.getInt(6));
                float rental_price = (cursor.getFloat(7));
                boolean furnished =cursor.getInt(8)== 1 ? true:false;
                byte [] images =cursor.getBlob(9);
                String availability_date = cursor.getString(10);
                String description = cursor.getString(11);
                boolean balcony =cursor.getInt(12)== 1 ? true:false;
                boolean garden = cursor.getInt(13)== 1 ? true:false;
                House post=new House(house_id,agencyEmail,city,postal_address,surface_area,costruction_year,number_of_bedrooms,rental_price,furnished, images,availability_date,description,balcony,garden);
                list.add(post);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        //System.out.println(list.toString());
        return list;
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }


}
