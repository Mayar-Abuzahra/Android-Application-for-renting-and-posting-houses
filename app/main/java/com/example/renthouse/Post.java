package com.example.renthouse;

import static android.app.Activity.RESULT_OK;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;


/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class Post extends Fragment {
    String agencyEmail;

    EditText post_city;
    EditText post_address;
    EditText post_area;
    EditText post_year;
    EditText post_rooms;
    EditText post_price;
    CheckBox post_furnished;
    CheckBox post_garden;
    CheckBox post_balcony;
    EditText post_date;
    EditText post_descrbtion;
    byte[] image = null;
    ImageButton pickImag;
    boolean clicked=false;

    Button Publish;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_post, container, false);


        HouseDB houseDB=new HouseDB(getActivity());
        post_city=(EditText)view.findViewById(R.id.post_city);
        post_address=(EditText)view.findViewById(R.id.post_address);
        post_area=(EditText)view.findViewById(R.id.post_area);
        post_rooms=(EditText)view.findViewById(R.id.post_rooms);
        post_price=(EditText)view.findViewById(R.id.post_price);
        post_year=(EditText)view.findViewById(R.id.post_year);
        post_date=(EditText)view.findViewById(R.id.post_date);
        post_descrbtion=(EditText)view.findViewById(R.id.post_describtion);
        post_furnished=(CheckBox)view.findViewById(R.id.post_furnished);
        post_balcony=(CheckBox)view.findViewById(R.id.post_balcony);
        post_garden=(CheckBox)view.findViewById(R.id.post_garden);
        pickImag=(ImageButton)view.findViewById(R.id.pickImg);
        Publish=(Button) view.findViewById(R.id.Publish);
        if(Entry.login){
            agencyEmail=Entry.useremial;
        }
        else{
            agencyEmail=SignUpAgency.agencyemail;
        }
        pickImag.setOnClickListener(view1 -> {
            openGalleries();
            clicked=true;
        });

        Publish.setOnClickListener((view1)-> {
            if(post_city.getText().toString().isEmpty()||post_address.getText().toString().isEmpty()||post_area.getText().toString().isEmpty()||post_rooms.getText().toString().isEmpty()||post_price.getText().toString().isEmpty()|| post_year.getText().toString().isEmpty()||post_date.getText().toString().isEmpty()||clicked==false){
                Toast.makeText(getActivity(), "Please pay attention to the entry fields  ", Toast.LENGTH_SHORT).show();
            }
            else {
                BitmapDrawable drawable = (BitmapDrawable) pickImag.getDrawable();
                Bitmap bitmap = drawable.getBitmap();
                image = getBytes(bitmap);
                House house=new House(agencyEmail,post_city.getText().toString(),post_address.getText().toString(),Float.parseFloat(post_area.getText().toString()),Long.parseLong(post_year.getText().toString()),Integer.parseInt(post_rooms.getText().toString()),Float.parseFloat(post_price.getText().toString()),post_furnished.isChecked(),image,post_date.getText().toString(),post_descrbtion.getText().toString(),post_balcony.isChecked(),post_garden.isChecked());
                System.out.println(house);
                boolean res=houseDB.insertHouse(house);
                Cursor cursor=houseDB.getAllHouses();
                while (cursor.moveToNext()){
                    String id=(cursor.getString(0));
                    System.out.println("********id**********"+id);
                }

                Toast.makeText(getActivity(),"Added Successfully ", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), HomeAgency.class);
                startActivity(intent);}

        });

        return  view;
    }



    public void openGalleries() {

        Intent intentImg = new Intent(Intent.ACTION_GET_CONTENT);
        intentImg.setType("image/*");
        startActivityForResult(intentImg, 100);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == 100) {

            Uri uri = data.getData();

            try {
                InputStream inputStream =getActivity().getContentResolver().openInputStream(uri);
                Bitmap decodeStream = BitmapFactory.decodeStream(inputStream);
                pickImag.setImageBitmap(decodeStream);

                image = getBytes(decodeStream);

            } catch (Exception ex) {
                Log.e("ex", ex.getMessage());
            }

        }
    }

    public static byte[] getBytes(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
        return stream.toByteArray();
    }
}