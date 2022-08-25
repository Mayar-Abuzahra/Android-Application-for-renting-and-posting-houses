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

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class Requests extends Fragment {

    public static int idRequest;
    ScrollView sv;
    String entryEmail;
    public  static List<AgencyToTenant> requests;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view1 =inflater.inflate(R.layout.fragment_requests, container, false);
        sv=(ScrollView) view1.findViewById(R.id.sv3);
        LinearLayout display = new LinearLayout(getActivity());
        display.setOrientation(LinearLayout.VERTICAL);
        if(Entry.login){
            entryEmail=Entry.useremial;
        }
        else{
            entryEmail=SignUpAgency.agencyemail;
        }

        AgencyToTenantDB agencytenantdb=new AgencyToTenantDB(getActivity());
        HouseDB houseDB=new HouseDB(getActivity());
        requests=agencytenantdb.requests(entryEmail);

        if(requests.size()==0){
            LinearLayout section = new LinearLayout(getActivity());
            section.setOrientation(LinearLayout.HORIZONTAL);
            TextView msg=new TextView(getActivity());
            msg.setText("There is no request yet ");
            msg.setTextSize(18);
            msg.setTextColor(Color.parseColor("#201E1E"));
            section.addView(msg);
            sv.addView(section);
        }
        else{



        for(int i=0;i<requests.size();i++){
            LinearLayout section = new LinearLayout(getActivity());
            section.setOrientation(LinearLayout.HORIZONTAL);
            Button btn1= new Button(getActivity());
            btn1.setId(i);
            btn1.setText("View more");
            LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params1.weight = 0.1f;
            btn1.setLayoutParams(params1);
            TextView houseId=new TextView(getActivity());
            TextView index=new TextView(getActivity());
            //houseId.setText("-Hello               ");
            houseId.setText("- The house with this id  "+requests.get(i).getHouseID()+"is requested");
            houseId.setTextSize(18);
            houseId.setTextColor(Color.parseColor("#201E1E"));
            index.setText((i+1)+"");
            index.setTextSize(18);
            index.setTextColor(Color.parseColor("#201E1E"));
            LinearLayout.LayoutParams params3 = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params3.weight = 0.9f;
            houseId.setLayoutParams(params3);
            index.setWidth(50);
            index.setHeight(100);
            section.addView(index);
            section.addView(houseId);
            System.out.println("---------------------------"+btn1.getId());
            section.addView(btn1);
            display.addView(section);
            btn1.setOnClickListener(view2 -> {
                idRequest=Integer.parseInt(index.getText().toString())-1;
                System.out.println(  Integer.parseInt(index.getText().toString())-1);
                Intent intent = new Intent(getActivity(), RequestDetails.class);
                startActivity(intent);
            });




        }
        sv.addView(display);
     }

        return view1;

    }



}