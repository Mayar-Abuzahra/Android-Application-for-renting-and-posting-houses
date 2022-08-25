package com.example.renthouse;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.List;

public class Edit extends Fragment {

ScrollView sv;
String entryEmail;
public  static  List<House> posts;
public static int index;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view1 =inflater.inflate(R.layout.fragment_edit, container, false);
        sv=(ScrollView) view1.findViewById(R.id.sv);
        LinearLayout display = new LinearLayout(getActivity());
        display.setOrientation(LinearLayout.VERTICAL);
        HouseDB housedb=new HouseDB(getActivity());
        if(Entry.login){
            entryEmail=Entry.useremial;
        }
        else{
            entryEmail=SignUpAgency.agencyemail;
        }
        System.out.println("**********************");
        posts=housedb.posts(entryEmail);
 if(posts.size()==0){
     LinearLayout section = new LinearLayout(getActivity());
     section.setOrientation(LinearLayout.HORIZONTAL);
     TextView msg=new TextView(getActivity());
     msg.setText("Sorry , you didn't publish any post yet so you can't edit anything! ");
     msg.setTextSize(18);
     msg.setTextColor(Color.parseColor("#201E1E"));
     section.addView(msg);
     sv.addView(section);
 }
else{
for(int i=0;i<posts.size();i++){
    LinearLayout section = new LinearLayout(getActivity());
    section.setOrientation(LinearLayout.HORIZONTAL);
    Button btn= new Button(getActivity());
    btn.setId(i);
    btn.setText((i+1)+" ");
    LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    params1.weight = 0.05f;
    btn.setLayoutParams(params1);
    TextView houseId=new TextView(getActivity());
    houseId.setText("City="+posts.get(i).getCity()+",Construction Year="+posts.get(i).getConstruction_year()+","+"\n For more information please press the button on the left");
    houseId.setTextSize(18);
    houseId.setTextColor(Color.parseColor("#201E1E"));
    houseId.setHeight(300);
    LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    params2.weight = 0.95f;
    houseId.setLayoutParams(params2);
    section.addView(btn);
    section.addView(houseId);
    display.addView(section);
    btn.setOnClickListener(view2 -> {

        index=Integer.parseInt(btn.getText().toString().trim())-1;
        Intent intent = new Intent(getActivity(), EditPosts.class);
        startActivity(intent);
    });
}
sv.addView(display);}






      return view1;

    }



}