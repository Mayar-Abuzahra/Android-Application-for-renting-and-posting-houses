package com.example.renthouse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class SearchSpecificResult extends AppCompatActivity {
    LinearLayout l;
    String str="";
    String str2="";
    static int i=0;
    private ImageView img;
    boolean clicked=false;
    int width=1350;
    int height=800;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_specific_result);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        l=(LinearLayout) findViewById(R.id.linearlayout2);

        img = (ImageView) findViewById(R.id.img);
        img.setImageResource(R.drawable.gradient_list2);
        AnimationDrawable an=(AnimationDrawable) img.getDrawable();
        an.start();
        img.setLayoutParams(new LinearLayout.LayoutParams(2000, 160));
        LinearLayout.LayoutParams params3 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params3.setMargins(450, 3, 3, 3);
        img.setLayoutParams(params3);

        AgencyToTenantDB req=new AgencyToTenantDB(this);
        TextView temp=new TextView(this);
        Button btn,return_btn;

        HouseDB home=new HouseDB(this);
        str = "SELECT * FROM House where house_id="+SearchResults.index;

        Cursor c=home.getNeededHouses(str);
        if (c.moveToFirst()) {
            do{
                str2=(" House identification number:" + Integer.toString(c.getInt(0)));
                str2+=("\n\n Agency email:" + (c.getString(1)));
                str2+=("\n\n City:" + (c.getString(2)));
                str2+=("\n\n Postal address:" + (c.getString(3)));
                str2+=("\n\n Surface Area is " + Float.toString(c.getFloat(4)));
                str2+=("\n\n Construction year is " + Long.toString(c.getLong(5)));
                str2+=("\n\n Number of bedrooms=" + Integer.toString(c.getInt(6)));
                str2+=("\n\n Rental price=" + Float.toString(c.getFloat(7)));
                str2+=("\n\n Furnished is " + (c.getInt(8)==1?true:false));
                str2+=("\n\n Availability date is " + (c.getString(10)));
                str2+=("\n\n Description is" + (c.getString(11)));
                str2+=("\n\n Balcony is " + (c.getInt(12)==1?true:false));
                str2+=("\n\n Garden is "+(c.getInt(13)==1?true:false));
                btn = new Button(this);
                return_btn= new Button(this);
                btn.setBackgroundColor(Color.parseColor("#000000"));
                btn.setText("Apply");
                btn.setTextColor(Color.parseColor("#FFF8F8F8"));
                return_btn.setText("Return");
                return_btn.setTextColor(Color.parseColor("#000000"));
                temp.setTextSize(TypedValue.COMPLEX_UNIT_SP,18);

                temp.setText(str2); //arbitrary task
                temp.setGravity(Gravity.CENTER);
                temp.setTextColor(Color.parseColor("#000000"));

                ImageView img=new ImageView(this);
                Bitmap bitmap = BitmapFactory.decodeByteArray(c.getBlob(9), 0, c.getBlob(9).length);
                Bitmap bmp=Bitmap.createScaledBitmap(bitmap, width,height, true);
                img.setImageBitmap(bmp);
                l.addView(img);


                GradientDrawable gd = new GradientDrawable();
                gd.setColor(0xFFEBD5EF);
                gd.setCornerRadius(28);
                gd.setStroke(1, 0xFF005500);
                l.setBackground(gd);

                btn.setLayoutParams(new LinearLayout.LayoutParams(2000, 160));

                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                params.setMargins(200, 3, 3, 3);
                btn.setLayoutParams(params);
                temp.setTypeface(null, Typeface.BOLD);

                GradientDrawable gd2 = new GradientDrawable();
                gd2.setShape(GradientDrawable.OVAL);
                gd2.setCornerRadius(20);
                gd2.setStroke(1, 0xFF005500);
                return_btn.setBackground(gd2);

                return_btn.setLayoutParams(new LinearLayout.LayoutParams(2000, 160));

                LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                params2.setMargins(300, 3, 3, 3);
                return_btn.setLayoutParams(params2);

                GradientDrawable gd3 = new GradientDrawable();
                gd3.setColor(Color.parseColor("#000000"));
                gd3.setCornerRadius(20);
                gd3.setStroke(1, 0xFF005500);
                btn.setBackground(gd3);

                l.addView(temp);
                LinearLayout l3=new LinearLayout(this);
                l3.setOrientation(LinearLayout.HORIZONTAL);
                l3.addView(btn);
                l3.addView(return_btn);
                l.addView(l3);
                String lines[] = str2.split("\\r?\\n\\r?\\n");

                String[] newDesc = lines[0].split(":");
                String[] newDesc2 = lines[1].split(":");
                String[] newDesc3 = lines[2].split(":");
                String[] newDesc5 = lines[3].split(":");



                btn.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        String[] newDesc=lines[0].split(":");
                        String[] newDesc2=lines[1].split(":");
                        String[] newDesc3=lines[2].split(":");
                        System.out.println("****"+newDesc[1]+newDesc5[1]+newDesc2[1]);
                        if(clicked==false){
                            AgencyToTenant h=new AgencyToTenant(Integer. parseInt(newDesc[1]),newDesc3[1],newDesc5[1],1,newDesc2[1],Entry.useremial2,true);
                            boolean g=req.insertRequest(h);
                            if(g){
                                System.out.println("Added successfully");
                                Toast.makeText(SearchSpecificResult.this,"Saved successfully",Toast.LENGTH_SHORT);}
                            else
                                Toast.makeText(SearchSpecificResult.this,"Try again",Toast.LENGTH_SHORT);}
                        else
                            Toast.makeText(SearchSpecificResult.this,"You already have requested this home, wait",Toast.LENGTH_SHORT);
                        clicked=true;
                    }
                });

                return_btn.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        Intent intent = new Intent(SearchSpecificResult.this,SearchResults.class);
                        startActivity(intent);
                        finish();

                    }
                });

            }
            while (c.moveToNext());}

    }
}
