package com.example.renthouse;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.renthouse.databinding.FragmentHomeBinding;

import java.util.List;

public class HomeFragment extends Fragment {

    ScrollView sv;
    List<House> posts;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view1 =inflater.inflate(R.layout.fragment_home, container, false);
        sv=(ScrollView) view1.findViewById(R.id.sv1);
        LinearLayout display = new LinearLayout(getActivity());
        display.setOrientation(LinearLayout.VERTICAL);
        HouseDB houseDB=new HouseDB(getActivity());
        posts=houseDB.GetNew();
        if(posts.size()==0){
            LinearLayout section = new LinearLayout(getActivity());
            section.setOrientation(LinearLayout.HORIZONTAL);
            TextView msg=new TextView(getActivity());
            msg.setText("No posts yet");
            msg.setTextSize(18);
            msg.setTextColor(Color.parseColor("#201E1E"));
            section.addView(msg);
            sv.addView(section);
        }
        else {
            if (posts.size() >5) {
                for (int i = posts.size()-1; i>=posts.size()-5; i--) {
                    LinearLayout section = new LinearLayout(getActivity());
                    section.setOrientation(LinearLayout.VERTICAL);
                    ImageView img = new ImageView(getActivity());
                    //System.out.println("Length--------"+posts.get(i).getImages().length);
                    int width = 1450;
                    int height = 800;
                    Bitmap bitmap = BitmapFactory.decodeByteArray(posts.get(i).getImages(), 0, posts.get(i).getImages().length);
                    Bitmap bmp = Bitmap.createScaledBitmap(bitmap, width, height, true);
                    img.setImageBitmap(bmp);
                    TextView houseId = new TextView(getActivity());
                    //houseId.setText(posts.get(i).getAgencyEmail().toString());
                    houseId.setText("\t\tCity=" + posts.get(i).getCity() + "\t\t\tPostal address=" + posts.get(i).getPostal_address() + "\n\t\tRental price=" + posts.get(i).getRental_price());
                    houseId.setTextColor(Color.BLACK);
                    houseId.setTextSize(20);
                    TextView space = new TextView(getActivity());
                    space.setText("\n");
                    section.addView(space);
                    section.addView(img);
                    section.addView(houseId);
                    //section.setWeightSum(1);

                    display.addView(section);
                    //display.addView(space);


                }
                sv.addView(display);
            }
            else {
                for (int i = 0; i < posts.size(); i++) {
                    LinearLayout section = new LinearLayout(getActivity());
                    section.setOrientation(LinearLayout.VERTICAL);
                    ImageView img = new ImageView(getActivity());
                    //System.out.println("Length--------"+posts.get(i).getImages().length);
                    int width = 1450;
                    int height = 800;
                    Bitmap bitmap = BitmapFactory.decodeByteArray(posts.get(i).getImages(), 0, posts.get(i).getImages().length);
                    Bitmap bmp = Bitmap.createScaledBitmap(bitmap, width, height, true);
                    img.setImageBitmap(bmp);
                    TextView houseId = new TextView(getActivity());
                    //houseId.setText(posts.get(i).getAgencyEmail().toString());
                    houseId.setText("\t\tCity=" + posts.get(i).getCity() + "\t\t\tPostal address=" + posts.get(i).getPostal_address() + "\n\t\tRental price=" + posts.get(i).getRental_price());
                    houseId.setTextColor(Color.BLACK);
                    houseId.setTextSize(20);
                    TextView space = new TextView(getActivity());
                    space.setText("\n");
                    section.addView(space);
                    section.addView(img);
                    section.addView(houseId);
                    //section.setWeightSum(1);

                    display.addView(section);
                    //display.addView(space);


                }
                sv.addView(display);
            }
        }
        return view1;

    }



}