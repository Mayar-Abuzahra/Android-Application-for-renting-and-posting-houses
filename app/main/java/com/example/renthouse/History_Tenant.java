package com.example.renthouse;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class History_Tenant extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public History_Tenant() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TenantHistory.
     */
    // TODO: Rename and change types and number of parameters
    public static History_Tenant newInstance(String param1, String param2) {
        History_Tenant fragment = new History_Tenant();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    static Cursor c;
    String str="";
    TextView temp;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view3 = inflater.inflate(R.layout.fragment_history__tenant, container, false);
        LinearLayout l=(LinearLayout)view3.findViewById(R.id.linearlayout3);
        AgencyToTenantDB req=new AgencyToTenantDB(getActivity());
        c=req.getNeededTenantInfo("SELECT * FROM AgencytoTenant where tenantEmail=\""+Entry.useremial2+"\" and not waiting");
        if(c.getCount() != 0){
            if (c.moveToFirst())
            {   do{
                str+="\nCity:"+(c.getString(2));
                str+="\nPostal address:"+(c.getString(3));
                str+="\nRenting Period:"+Integer.toString(c.getInt(4));
                str+="\nAgency email:"+(c.getString(5));

                temp=new TextView(getActivity());
                temp.setText(str); //arbitrary task
                temp.setTextSize(18);
                temp.setTextColor(Color.parseColor("#000000"));
                l.addView(temp);
            }
            while (c.moveToNext());
            }
        }
        return view3;
    }
}