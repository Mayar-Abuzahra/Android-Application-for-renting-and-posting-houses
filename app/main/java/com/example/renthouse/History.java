package com.example.renthouse;

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
public class History extends Fragment {

    ScrollView sv;
    String entryEmail;
    List<AgencyToTenant> orders;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view1 =inflater.inflate(R.layout.fragment_history, container, false);
        sv=(ScrollView) view1.findViewById(R.id.sv2);
        LinearLayout display = new LinearLayout(getActivity());
        display.setOrientation(LinearLayout.VERTICAL);
        AgencyToTenantDB agencytenantdb=new AgencyToTenantDB(getActivity());
        if(Entry.login){
            entryEmail=Entry.useremial;
        }
        else{
            entryEmail=SignUpAgency.agencyemail;
        }
        orders=agencytenantdb.history(entryEmail);
        if(orders.size()==0){
            LinearLayout section = new LinearLayout(getActivity());
            section.setOrientation(LinearLayout.HORIZONTAL);
            TextView msg=new TextView(getActivity());
            msg.setText("Sorry , you didn't publish any post yet so you don't have any history yet! ");
            msg.setTextSize(18);
            msg.setTextColor(Color.parseColor("#201E1E"));
            section.addView(msg);
            sv.addView(section);
        }
        else{
            for(int i=0;i<orders.size();i++){

                TextView houseId=new TextView(getActivity());
                houseId.setText("\t\tCity="+orders.get(i).getCity()+"\tPostal address="+orders.get(i).getPostalAddress()+"\t Renting Period="+orders.get(i).getRentingPeriod()+"\t Tenant Email="+orders.get(i).getTenantEmail()+"\n");
                houseId.setTextSize(18);
                houseId.setTextColor(Color.parseColor("#201E1E"));
                display.addView(houseId);


            }
            sv.addView(display);}

        return view1;

    }



}