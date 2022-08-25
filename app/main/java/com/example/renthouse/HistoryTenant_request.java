package com.example.renthouse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class HistoryTenant_request extends AppCompatActivity {
    static Cursor c;
    String str="";
    TextView temp;
    Button return_toprofile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_tenant_request);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        LinearLayout l=(LinearLayout)findViewById(R.id.linearlayout3);
        AgencyToTenantDB req=new AgencyToTenantDB(HistoryTenant_request.this);
        c=req.getNeededTenantInfo("SELECT * FROM AgencytoTenant where tenantEmail=\""+RequestDetails.tenantEmail+"\" and not waiting");
        if(c.getCount() != 0){
            if (c.moveToFirst())
            {   do{
                str+="\n\tCity:"+(c.getString(2));
                str+="\n\tPostal address:"+(c.getString(3));
                str+="\n\tRenting Period:"+Integer.toString(c.getInt(4));
                str+="\n\tAgency email:"+(c.getString(5)+"\n");

                temp=new TextView(HistoryTenant_request.this);
                temp.setText(str); //arbitrary task
                temp.setTextSize(20);
                temp.setTextColor(Color.BLACK);
                l.addView(temp);
            }
            while (c.moveToNext());
            }
        }
        return_toprofile=new Button(HistoryTenant_request.this);
        return_toprofile.setText("Return");
        return_toprofile.setTextColor(Color.WHITE);
        return_toprofile.setBackgroundColor(Color.BLACK);
        TextView space=new TextView(HistoryTenant_request.this);
        l.addView(return_toprofile);
        //l.addView(space);
        return_toprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(HistoryTenant_request.this,RequestDetails.class);
                startActivity(intent);
                finish();
            }
        });
    }
}