package com.example.renthouse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Pattern;

public class SignUpTenant extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    EditText emailAddress, firstName, lastName,password,confirmPassword,gms,occ, familySize,city, phoneNumber;
    Button signup;
    Spinner gender,nationality,crc;
    String[] gender2 = {"","Male", "Female"};
    String[] nationality2 = {"","Palestinian", "Jordanian","Guinean","Sudanese","Rwandan","Moroccan"};
    String[] country2 = {"","Palestine", "Jordan","Guinea","Sudan","Rwanda","Morocco"};
    public final Pattern textPattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$, %, #, @, !, {,}]).+$");
    TenantDB db;
    AgencyDB agencyDB;
    Tenant t;
    public static String tenantEmail;
    public static boolean sign2=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_tenant);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        emailAddress=(EditText) findViewById(R.id.ea2);
        firstName =(EditText) findViewById(R.id. editText1);
        lastName =(EditText) findViewById(R.id.city);
        password=(EditText) findViewById(R.id. editText3);
        confirmPassword=(EditText) findViewById(R.id. editText4);
        gms=(EditText) findViewById(R.id. editText5);
        occ=(EditText) findViewById(R.id. editText6);
        familySize =(EditText) findViewById(R.id. editText7);
        city=(EditText) findViewById(R.id. editText9);
        phoneNumber =(EditText) findViewById(R.id. editText10);
        signup=(Button) findViewById(R.id. button4);
        gender=(Spinner) findViewById(R.id. spinner);
        nationality=(Spinner) findViewById(R.id. spinner2);
        crc=(Spinner) findViewById(R.id. spinner3);
        db=new TenantDB(this);
        agencyDB=new AgencyDB(this);


        AnimationDrawable ad= (AnimationDrawable) gender.getBackground();
        ad.setEnterFadeDuration(200);
        ad.setExitFadeDuration(500);
        ad.start();

        AnimationDrawable ad2= (AnimationDrawable) nationality.getBackground();
        ad2.setEnterFadeDuration(200);
        ad2.setExitFadeDuration(500);
        ad2.start();

        AnimationDrawable ad3= (AnimationDrawable) crc.getBackground();
        ad3.setEnterFadeDuration(200);
        ad3.setExitFadeDuration(500);
        ad3.start();

        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item, gender2);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gender.setAdapter(aa);
        gender.setOnItemSelectedListener(this);

        ArrayAdapter aa2 = new ArrayAdapter(this,android.R.layout.simple_spinner_item, nationality2);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        nationality.setAdapter(aa2);
        nationality.setOnItemSelectedListener(this);

        ArrayAdapter aa3 = new ArrayAdapter(this,android.R.layout.simple_spinner_item, country2);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        crc.setAdapter(aa3);
        crc.setOnItemSelectedListener(this);

        signup.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                boolean validEmail=validateEmail();
                boolean validFirstName=validateFirstName();
                boolean validLastName=validateLastName();
                boolean validPassword=validatePassword();
                boolean validFamilySize=validateFamilySize();
                boolean validGrossMonthlySalary=ValidateGrossMonthlySalary();
                boolean validCity=validateCity();
                boolean validPhoneNumber=validatePhoneNumber();
                if(validEmail && validFirstName && validLastName && validPassword && validFamilySize && validGrossMonthlySalary && validCity && validPhoneNumber){
                    Toast.makeText(SignUpTenant.this,"Registered successfully",Toast.LENGTH_SHORT);


                    MessageDigest md= null;
                    try {
                        md = MessageDigest.getInstance("MD5");
                    } catch (NoSuchAlgorithmException e) {
                        e.printStackTrace();
                    }
                    md.update(password.getText().toString().getBytes());
                    byte[] rba=md.digest();
                    StringBuilder sb=new StringBuilder();
                    for(byte b:rba){
                        sb.append(String.format("%02x",b));
                    }
                    t=new Tenant(emailAddress.getText().toString(), firstName.getText().toString(), lastName.getText().toString(),gender.getSelectedItem().toString(),sb.toString(),nationality.getSelectedItem().toString(),Integer.parseInt(gms.getText().toString()),occ.getText().toString(),Integer.parseInt(familySize.getText().toString()),crc.getSelectedItem().toString(),city.getText().toString(), phoneNumber.getText().toString());
                    boolean r=db.insertUser(t);
                    if(r) {
                        Toast.makeText(SignUpTenant.this, "Data has been inserted", Toast.LENGTH_SHORT).show();
                        tenantEmail=emailAddress.getText().toString();
                        sign2=true;
                        Intent in=new Intent(SignUpTenant.this,HomeTenant.class);
                        startActivity(in);
                        finish();
                    }
                }
            }
        });

        crc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch(position) {

                    case 1 :
                        phoneNumber.setText("00970");
                        break;

                    case 2 :
                        phoneNumber.setText("00962");
                        break;
                    case 3 :
                        phoneNumber.setText("240");
                        break;
                    case 4 :
                        phoneNumber.setText("249");
                        break;
                    case 5 :
                        phoneNumber.setText("250");
                        break;
                    case 6 :
                        phoneNumber.setText("212");
                        break;

                    default :
                        phoneNumber.setText("");

                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private boolean validateFirstName(){
        String str= firstName.getText().toString().trim();
        if(str.isEmpty()){
            firstName.setError("First name can not be empty, fill it");
            firstName.setBackgroundColor(Color.parseColor("#DC0909"));
            return false;
        }
        else{
            if(str.length() >= 3 || str.length() <= 20){
                firstName.setError(null);
                return true;
            }
            else{
                firstName.setError("First name must be between 3 to 20 characters only");
                firstName.setBackgroundColor(Color.parseColor("#DC0909"));
            }
            return false;
        }
    }

    private boolean validateLastName(){
        String str= lastName.getText().toString().trim();
        if(str.isEmpty()){
            lastName.setError("Last name can not be empty, fill it");
            lastName.setBackgroundColor(Color.parseColor("#DC0909"));
            return false;
        }
        else{
            if(str.length() >= 3 || str.length() <= 20){
                lastName.setError(null);
                return true;
            }
            else{
                lastName.setError("Last name must be between 3 to 20 characters only");
                lastName.setBackgroundColor(Color.parseColor("#DC0909"));}
            return false;
        }
    }

    private boolean validateEmail(){
        String str=emailAddress.getText().toString().trim();
        //String ce="[a-zA-Z0-9._-]+@[a-z]+\\+.+[a-z].";
        if(str.isEmpty()){
            emailAddress.setError("Email can not be empty, fill it");
            emailAddress.setBackgroundColor(Color.parseColor("#DC0909"));
            return false;
        }
        else{
            boolean repeatedEmail=db.checkEmailAddress(emailAddress.getText().toString());
            boolean repeatedEmail2=agencyDB.checkEmailAddress(emailAddress.getText().toString());

            if(repeatedEmail||repeatedEmail2){
                emailAddress.setError("This email was token");
                emailAddress.setBackgroundColor(Color.parseColor("#DC0909"));
                return false;
            }
            else{
                if(Patterns.EMAIL_ADDRESS.matcher(str).matches()){
                    emailAddress.setError(null);
                    return true;
                }
                else{
                    emailAddress.setError("Invalid email");
                    emailAddress.setBackgroundColor(Color.parseColor("#DC0909"));
                    return false;}
            }
        }}

    private boolean validatePassword(){
        String str=password.getText().toString().trim();
        String str2=confirmPassword.getText().toString().trim();
        /*String cp="(?=.*[$, %, #, @, !, {,}])";
        String cp2="(?=.*[a-z])";
        String cp3="(?=.*[A-Z]) ";
        String cp5="(?=.*\\\\d)";*/
        if(str.isEmpty()){
            password.setError("Password can not be empty, fill it");
            password.setBackgroundColor(Color.parseColor("#DC0909"));
            return false;
        }
        else{
            if((str.length() > 7 || str.length() <= 15) && (textPattern.matcher(str).matches())){
                if(str.equals(str2)){
                    password.setError(null);
                    return true;
                }
                else{
                    password.setError("Passwords mismatch");
                    password.setBackgroundColor(Color.parseColor("#DC0909"));
                    return false;
                }
            }
            else {
                password.setError("Invalid password");
                password.setBackgroundColor(Color.parseColor("#DC0909"));
                return false;
            }
        }
    }

    private boolean validateFamilySize(){
        String str= familySize.getText().toString().trim();
        int d;
        if(str.isEmpty()){
            familySize.setError("Family size can not be empty, fill it");
            familySize.setBackgroundColor(Color.parseColor("#DC0909"));
            return false;
        }
        else{
            try {
                d = Integer.parseInt(str);
                familySize.setError(null);
                return true;
            } catch (NumberFormatException nfe) {
                familySize.setError("Invalid family size");
                familySize.setBackgroundColor(Color.parseColor("#DC0909"));
                return false;
            }
        }
    }

    private boolean ValidateGrossMonthlySalary(){
        String str=gms.getText().toString().trim();

        if(str.isEmpty()){
            gms.setError("Gross Monthly Salary can not be empty, fill it");
            gms.setBackgroundColor(Color.parseColor("#DC0909"));
            return false;
        }
        else{
            try {
                int d = Integer.parseInt(str);
                gms.setError(null);
                return true;
            } catch (NumberFormatException nfe) {
                gms.setError("Invalid Gross Monthly Salary");
                gms.setBackgroundColor(Color.parseColor("#DC0909"));
                return false;
            }
        }
    }

    private boolean validateCity(){
        String str=city.getText().toString().trim();
        String ce=",";
        if(str.isEmpty()){
            city.setError("City can not be empty, fill it");
            city.setBackgroundColor(Color.parseColor("#DC0909"));
            return false;
        }
        else{
            if(str.contains(ce)){
                city.setError(null);
                return true;
            }
            else{
                city.setError("Invalid city");
                city.setBackgroundColor(Color.parseColor("#DC0909"));
                return false;
            }}
    }

    private boolean validatePhoneNumber(){
        String str= phoneNumber.getText().toString().trim();

        if(str.isEmpty()){
            phoneNumber.setError("Phone number can not be empty, fill it");
            phoneNumber.setBackgroundColor(Color.parseColor("#DC0909"));
            return false;
        }
        else{
            if(PhoneNumberUtils.isGlobalPhoneNumber(str)){
                phoneNumber.setError(null);
                return true;
            }
            else{
                phoneNumber.setError("Invalid phone number");
                phoneNumber.setBackgroundColor(Color.parseColor("#DC0909"));
                return false;
            }}
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String s=parent.getItemAtPosition(position).toString();
        Toast.makeText(this,s,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}