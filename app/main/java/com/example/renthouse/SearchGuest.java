package com.example.renthouse;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class SearchGuest extends AppCompatActivity {
    String str = "";
    EditText city, minimum_surface_area, maximum_surface_area, minimum_number_of_bedrooms, maximum_number_of_bedrooms, minimum_number_of_rental_price;
    CheckBox balcony, garden, furnished;
    int count = 0;
    boolean flag = false;
    String city2, minimum_surface_area2, maximum_surface_area2, minimum_number_of_bedrooms2, maximum_number_of_bedrooms2, minimum_number_of_rental_price2;
    Button search;
    static Cursor c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_guest);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        search = (Button) findViewById(R.id.button9);
        city = (EditText) findViewById(R.id.city);
        minimum_surface_area = (EditText) findViewById(R.id.minimum_surface_area);
        maximum_surface_area = (EditText) findViewById(R.id.maximum_surface_area);
        minimum_number_of_bedrooms = (EditText) findViewById(R.id.minimum_number_of_bedrooms);
        maximum_number_of_bedrooms = (EditText) findViewById(R.id.maximum_number_of_bedrooms);
        minimum_number_of_rental_price = (EditText) findViewById(R.id.minimum_number_of_rental_price);
        furnished = (CheckBox) findViewById(R.id.checkBox);
        balcony = (CheckBox) findViewById(R.id.balcony);
        garden = (CheckBox) findViewById(R.id.garden);
        HouseDB home=new HouseDB(this);


        search.setOnClickListener((view2)-> {
            flag = false;
            city2 = city.getText().toString().toLowerCase();
            minimum_surface_area2 = minimum_surface_area.getText().toString();
            maximum_surface_area2 = maximum_surface_area.getText().toString();
            minimum_number_of_bedrooms2 = minimum_number_of_bedrooms.getText().toString();
            maximum_number_of_bedrooms2 = maximum_number_of_bedrooms.getText().toString();
            minimum_number_of_rental_price2 = minimum_number_of_rental_price.getText().toString();

            str = "SELECT * FROM House where ";
            if (!city2.isEmpty())
                count += 1;
            if (!minimum_surface_area2.isEmpty())
                count += 1;
            if (!maximum_surface_area2.isEmpty())
                count += 1;
            if (!minimum_number_of_bedrooms2.isEmpty())
                count += 1;
            if (!maximum_number_of_bedrooms2.isEmpty())
                count += 1;
            if (!minimum_number_of_rental_price2.isEmpty())
                count += 1;


            if ((!city2.isEmpty()) && (count != 0)) {
                str += "city= \""+city2+"\"";
                count -= 1;
                flag = true;
            }
            if ((!minimum_surface_area2.isEmpty()) && (count != 0)) {
                if (!flag)
                    flag = true;
                else
                    str += " AND ";
                str += "surface_area >= " + Integer.parseInt(minimum_surface_area2);
                count -= 1;
            }
            if ((!maximum_surface_area2.isEmpty()) && (count != 0)) {
                if (!flag)
                    flag = true;
                else
                    str += " AND ";
                str += "surface_area <= " + Integer.parseInt(maximum_surface_area2);
                count -= 1;
            }
            if ((!minimum_number_of_bedrooms2.isEmpty()) && (count != 0)) {
                if (!flag)
                    flag = true;
                else
                    str += " AND ";
                str += "number_of_bedrooms >= " + Integer.parseInt(minimum_number_of_bedrooms2);
                count -= 1;
            }
            if ((!maximum_number_of_bedrooms2.isEmpty()) && (count != 0)) {
                if (!flag)
                    flag = true;
                else
                    str += " AND ";
                str += "number_of_bedrooms <= " + Integer.parseInt(maximum_number_of_bedrooms2);
                count -= 1;
            }
            if ((!minimum_number_of_rental_price2.isEmpty()) && (count != 0)) {
                if (!flag)
                    flag = true;
                else
                    str += " AND ";
                str += "minimum_number_of_rental_price= " + Integer.parseInt(minimum_number_of_rental_price2);
                count -= 1;
            }
            if ((furnished.isChecked())) {
                if (!flag)
                    flag = true;
                else
                    str += " AND ";
                str += "furnished";
            }
            if ((balcony.isChecked())) {
                if (!flag)
                    flag = true;
                else
                    str += " AND ";
                str += "balcony";
            }
            if ((garden.isChecked())) {
                if (!flag)
                    flag = true;
                else
                    str += " AND ";
                str += "garden";
            }
            c = home.getNeededHouses(str);
            if(c.getCount() != 0){
                Intent in=new Intent(SearchGuest.this,SearchGuestResults.class);
                startActivity(in);
            }
        });

    }
}
