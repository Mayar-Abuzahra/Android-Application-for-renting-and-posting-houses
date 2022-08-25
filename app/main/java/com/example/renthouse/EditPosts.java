package com.example.renthouse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class EditPosts extends AppCompatActivity {
    EditText editposts_city;
    EditText editposts_address;
    EditText editpsots_area;
    EditText editpsots_year;
    EditText editpsots_rooms;
    EditText editpsots_date;
    CheckBox editpsots_furnished;
    CheckBox editpsots_balcony;
    CheckBox editpsots_garden;
    EditText editpsots_description;
    EditText editpsots_price;
    Button editposts_edit;
    Button editposts_delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_edit_posts);
        HouseDB houseDB=new HouseDB(EditPosts.this);
        System.out.println(Edit.posts + "*************************" + Edit.index);
        editposts_city = (EditText) findViewById(R.id.editposts_city);
        editposts_address = (EditText) findViewById(R.id.editposts_address);
        editpsots_area = (EditText) findViewById(R.id.editpsots_area);
        editpsots_year = (EditText) findViewById(R.id.editpsots_year);
        editpsots_rooms = (EditText) findViewById(R.id.editpsots_rooms);
        editpsots_date = (EditText) findViewById(R.id.editpsots_date);
        editpsots_description = (EditText) findViewById(R.id.editpsots_description);
        editpsots_price = (EditText) findViewById(R.id.editpsots_price);
        editpsots_furnished = (CheckBox) findViewById(R.id.editpsots_furnished);
        editpsots_balcony = (CheckBox) findViewById(R.id.editpsots_balcony);
        editpsots_garden = (CheckBox) findViewById(R.id.editpsots_garden);


        editposts_city.setText(Edit.posts.get(Edit.index).getCity());
        editposts_address.setText(Edit.posts.get(Edit.index).getPostal_address());
        Float area = Edit.posts.get(Edit.index).getSurface_area();
        editpsots_area.setText(area + "");
        long year = Edit.posts.get(Edit.index).getConstruction_year();
        editpsots_year.setText(year + "");
        int room = Edit.posts.get(Edit.index).getNumber_of_bedrooms();
        editpsots_rooms.setText(room + "");
        editpsots_date.setText(Edit.posts.get(Edit.index).getAvailability_date());
        editpsots_description.setText(Edit.posts.get(Edit.index).getDescription());
        float price = Edit.posts.get(Edit.index).getRental_price();
        editpsots_price.setText(price + "");
        editpsots_furnished.setChecked(Edit.posts.get(Edit.index).isFurnished());
        editpsots_balcony.setChecked(Edit.posts.get(Edit.index).isBalcony());
        editpsots_garden.setChecked(Edit.posts.get(Edit.index).isGarden());


        editposts_edit = (Button) findViewById(R.id.EditPosts_button);
        editposts_delete = (Button) findViewById(R.id.editposts_delete);
        editposts_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
if(editposts_city.getText().toString().isEmpty()||editposts_address.getText().toString().isEmpty()||editpsots_area.getText().toString().isEmpty()||editpsots_year.getText().toString().isEmpty()|| editpsots_rooms.getText().toString().isEmpty()||editpsots_date.getText().toString().isEmpty()||editpsots_price.getText().toString().isEmpty()){
    Toast.makeText(EditPosts.this,"Please fill all the fields ", Toast.LENGTH_SHORT).show();
}
else{


    boolean resultOfUpdate=houseDB.updatePost(Edit.posts.get(Edit.index).getHouse_id(),editposts_city.getText().toString(),editposts_address.getText().toString(),Float.parseFloat(editpsots_area.getText().toString()),Long.parseLong(editpsots_year.getText().toString()),Integer.parseInt(editpsots_rooms.getText().toString()),Float.parseFloat(editpsots_price.getText().toString()),editpsots_date.getText().toString(),editpsots_description.getText().toString(),editpsots_furnished.isChecked(),editpsots_garden.isChecked(),editpsots_balcony.isChecked());
    Toast.makeText(EditPosts.this,"Updated Successfully ", Toast.LENGTH_SHORT).show();
    Intent intent = new Intent(EditPosts.this, HomeAgency.class);
    startActivity(intent);
    finish();
}

            }

        });

        editposts_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            houseDB.deletePost(Edit.posts.get(Edit.index).getHouse_id());
                Toast.makeText(EditPosts.this,"Deleted Successfully ", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(EditPosts.this, HomeAgency.class);
                startActivity(intent);
                finish();



            }
        });
    }
}