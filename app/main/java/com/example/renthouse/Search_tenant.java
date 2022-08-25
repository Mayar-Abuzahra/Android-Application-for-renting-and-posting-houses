package com.example.renthouse;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class Search_tenant extends Fragment {
    String str = "";
    EditText city, minimum_surface_area, maximum_surface_area, minimum_number_of_bedrooms, maximum_number_of_bedrooms, minimum_number_of_rental_price;
    CheckBox balcony, garden, furnished;
    int count = 0;
    boolean flag = false;
    String city2, minimum_surface_area2, maximum_surface_area2, minimum_number_of_bedrooms2, maximum_number_of_bedrooms2, minimum_number_of_rental_price2;
    Button search;
    static Cursor c;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view1 = inflater.inflate(R.layout.fragment_search_tenant, container, false);
        search = (Button) view1.findViewById(R.id.button9);
        city = (EditText) view1.findViewById(R.id.city);
        minimum_surface_area = (EditText) view1.findViewById(R.id.minimum_surface_area);
        maximum_surface_area = (EditText) view1.findViewById(R.id.maximum_surface_area);
        minimum_number_of_bedrooms = (EditText) view1.findViewById(R.id.minimum_number_of_bedrooms);
        maximum_number_of_bedrooms = (EditText) view1.findViewById(R.id.maximum_number_of_bedrooms);
        minimum_number_of_rental_price = (EditText) view1.findViewById(R.id.minimum_number_of_rental_price);
        furnished = (CheckBox) view1.findViewById(R.id.checkBox);
        balcony = (CheckBox) view1.findViewById(R.id.balcony);
        garden = (CheckBox) view1.findViewById(R.id.garden);
        HouseDB home=new HouseDB(getActivity());
        /*House h=new House( "dkkdk@hfj.com", "Ramallah", "ll", 500, 2333, 2, 3, false, null, "2/3/2000", "hhh", false, true);
        boolean g=home.insertHouse(h);*/

        search.setOnClickListener((view2)-> {
            flag = false;
            city2 = city.getText().toString().toLowerCase().toLowerCase();
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
                str += "rental_price= " + Integer.parseInt(minimum_number_of_rental_price2);
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

            if(city2.isEmpty()&&minimum_surface_area2.isEmpty()&&maximum_surface_area2.isEmpty()&&minimum_number_of_bedrooms2.isEmpty()&&maximum_number_of_bedrooms2.isEmpty()&&minimum_number_of_rental_price2.isEmpty()&&(!furnished.isChecked())&&(!balcony.isChecked())&&(!garden.isChecked()))
                str = "SELECT * FROM House";

            c = home.getNeededHouses(str);

            if(c.getCount() != 0){
                Intent in=new Intent(getActivity(),SearchResults.class);
                startActivity(in);
            }
            else
                Toast.makeText(getActivity(), "Empty Results", Toast.LENGTH_SHORT).show();

        });

        return view1;
    }
}