package com.example.renthouse;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class RequestDetails extends AppCompatActivity {
    int index;
    ImageButton Return;
    Button approve_request;
    Button decline_request;
    Button history_request;
    TextView location_text;
    TextView familySize_text;
    TextView money_text;
    TextView phone_text;
    TextView email_text;
    TextView name_text;
    String FirstName;
    String LastName;
    String GrossMonthlySalary;
    String familySize;
    String country;
    String  city;
    String phone;
    private static final int NOTIFICATION_ID = 123;
    private static final String NOTIFICATION_TITLE = "Approve";
    private static final String NOTIFICATION_BODY = "We approved your request";
    private static final String NOTIFICATION_TITLE2 = "Decline";
    private static final String NOTIFICATION_BODY2 = "We declined your request";
    private static final String MY_CHANNEL_ID = "my_chanel_1";
    private static final String MY_CHANNEL_NAME = "My channel";

    public static String tenantEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("RequestDetails========"+index);
        setContentView(R.layout.activity_request_details);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        index=Requests.idRequest;
        Return=(ImageButton)findViewById(R.id.Return);
        approve_request=(Button)findViewById(R.id.approve_request);
        decline_request=(Button)findViewById(R.id.decline_request);
        history_request=(Button)findViewById(R.id.history_request);
        location_text=(TextView)findViewById(R.id.location_text);
        familySize_text=(TextView)findViewById(R.id.familySize_text);
        money_text=(TextView)findViewById(R.id.money_text);
        phone_text=(TextView)findViewById(R.id.phone_text);
        email_text=(TextView)findViewById(R.id.email_text);
        name_text=(TextView)findViewById(R.id.name_text);
        AgencyToTenantDB agencytenantdb=new AgencyToTenantDB(RequestDetails.this);
        HouseDB houseDB=new HouseDB(RequestDetails.this);
        TenantDB tenantDB= new TenantDB(RequestDetails.this,"Login.db",null,3);
        Cursor cursor=tenantDB.getAllInformation(Requests.requests.get(index).getTenantEmail());
        while (cursor.moveToNext()){
            FirstName=(cursor.getString(1));
            System.out.println(FirstName);
            LastName=(cursor.getString(2));
            System.out.println(LastName);
            GrossMonthlySalary=(cursor.getString(6));
            System.out.println(GrossMonthlySalary);
            familySize=(cursor.getString(8));
            System.out.println(familySize);
            country=(cursor.getString(9));
            System.out.println(country);
            city=(cursor.getString(10));
            System.out.println(city);
            phone=(cursor.getString(11));
            System.out.println(phone);
        }
        email_text.setText(Requests.requests.get(index).getTenantEmail());
        email_text.setTextSize(16);
        tenantEmail=Requests.requests.get(index).getTenantEmail().toString();
        name_text.setText(FirstName+" "+LastName);
        name_text.setTextSize(30);
        name_text.setTextColor(Color.BLACK);
        location_text.setText(country+": "+city);
        location_text.setTextSize(16);
        familySize_text.setText(familySize);
        familySize_text.setTextSize(16);
        money_text.setText(GrossMonthlySalary);
        money_text.setTextSize(16);
        phone_text.setText(phone);
        phone_text.setTextSize(16);

        Return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RequestDetails.this, HomeAgency.class);
                startActivity(intent);
                finish();

            }
        });

        approve_request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                agencytenantdb.approveRequest(Requests.requests.get(index).getAgencyTOTenantID());
                houseDB.deletePost(Requests.requests.get(index).getHouseID());
                Toast.makeText(RequestDetails.this, "Successfully accept", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RequestDetails.this, HomeAgency.class);
                startActivity(intent);
                finish();
                createNotification(NOTIFICATION_TITLE,NOTIFICATION_BODY);



            }
        });
        decline_request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                agencytenantdb.deleteRequest(Requests.requests.get(index).getAgencyTOTenantID());
                Toast.makeText(RequestDetails.this, "Successfully decline", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RequestDetails.this, HomeAgency.class);
                startActivity(intent);
                finish();
                createNotification(NOTIFICATION_TITLE2,NOTIFICATION_BODY2);

            }
        });

        history_request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RequestDetails.this, HistoryTenant_request.class);
                startActivity(intent);
                finish();

            }
        });




    }
    public void createNotification(String title, String body) {
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent, 0);
        createNotificationChannel();
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,
                MY_CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(body)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(body))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent);
        NotificationManagerCompat notificationManager =
                NotificationManagerCompat.from(this);
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }
    private void createNotificationChannel() {
        int importance = NotificationManager.IMPORTANCE_DEFAULT;
        NotificationChannel channel = new NotificationChannel(MY_CHANNEL_ID,
                MY_CHANNEL_NAME, importance);
        NotificationManager notificationManager =
                getSystemService(NotificationManager.class);
        if (notificationManager != null) {
            notificationManager.createNotificationChannel(channel);
        }
    }

}