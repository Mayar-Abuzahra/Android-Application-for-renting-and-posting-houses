package com.example.renthouse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Entry extends AppCompatActivity {
    Button SignUpButton;
    Button loginButton;
    EditText Email;
    EditText Passwordlogin;
    CheckBox Remember;
    StringBuilder encryptedPassword;
    SharedPreferences sharepref1;
    SharedPreferences.Editor myedit;
    public  static String useremial,useremial2;
    public  static  boolean login =false;
    public  static  boolean login2 =false;
    Button EnterGuset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entery);
        // remove actionBar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        SignUpButton=(Button) findViewById(R.id.SignUpButton);
        loginButton=(Button) findViewById(R.id.login);
        Email=(EditText)findViewById(R.id.Email);
        Passwordlogin=(EditText)findViewById(R.id.PasswordLogin);
        Remember=(CheckBox)findViewById(R.id.Remember);
        EnterGuset=(Button)findViewById(R.id.EnterGuset);
        sharepref1=getSharedPreferences("Mypref",MODE_PRIVATE);
        if(!sharepref1.toString().isEmpty()){
            Email.setText(sharepref1.getString("data",""));
        }

        SignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Entry.this,UserType.class);
                startActivity(intent);
                finish();
            }
        });
        EnterGuset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Entry.this,SearchGuest.class);
                startActivity(intent);
                finish();
            }
        });


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AgencyDB agencyDB=new AgencyDB(Entry.this);
                TenantDB tenantDB=new TenantDB(Entry.this);
                boolean isValidEmail=agencyDB.checkEmailAddress(Email.getText().toString());
                boolean isValidEmail2=tenantDB.checkEmailAddress(Email.getText().toString());
                if(Email.getText().length()==0||Passwordlogin.getText().length()==0) {
                    Toast.makeText(Entry.this, "Please make sure that you fill all the fields", Toast.LENGTH_SHORT).show();}
                else {
                    if (isValidEmail) {
                        useremial=Email.getText().toString();
                        encryptedPassword();
                        String encryptionpass=encryptedPassword.toString();
                        boolean isValidPassword = agencyDB.checkPassword(Email.getText().toString(), encryptionpass);
                        if (isValidPassword) {
                            login=true;
                            Intent intent = new Intent(Entry.this, HomeAgency.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(Entry.this, "Incorrect password", Toast.LENGTH_SHORT).show();


                        }
                    }
                    else if(isValidEmail2){
                        useremial2=Email.getText().toString();
                        encryptedPassword();
                        String encryptionpass=encryptedPassword.toString();
                        boolean isValidPassword = tenantDB.checkPassword(Email.getText().toString(), encryptionpass);
                        if (isValidPassword) {
                            login2=true;
                            Intent intent = new Intent(Entry.this,HomeTenant.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(Entry.this, "Incorrect password", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(Entry.this, "Invalid Email,Please sign up", Toast.LENGTH_SHORT).show();
                    }
                }
                if(Remember.isChecked()){
                    myedit=sharepref1.edit();
                    myedit.putString("data",Email.getText().toString());
                    myedit.commit();
                }
            }
        });
    }

    public void encryptedPassword(){
        MessageDigest md= null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        md.update(Passwordlogin.getText().toString().getBytes());
        byte[] rba=md.digest();
        encryptedPassword=new StringBuilder();
        for(byte b:rba){
            encryptedPassword.append(String.format("%02x",b));
        }
    }
}
