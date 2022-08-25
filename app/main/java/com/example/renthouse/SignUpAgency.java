package com.example.renthouse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
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

public class SignUpAgency extends AppCompatActivity {
    Spinner CountrySpinner;
    Button CreatAgencyAccount;
    EditText Emailaddress;
    EditText AgencyName;
    EditText Password;
    EditText ConfirmPassword;
    EditText City;
    EditText Phone;
    StringBuilder encryptedPassword;
    public static String agencyemail;
    public static boolean sign=false;
    AgencyDB agencyDB = new AgencyDB(SignUpAgency.this);
    TenantDB tenantDB=new TenantDB(SignUpAgency.this);
    // DataBaseHelper agencyDB=new DataBaseHelper(SignUpAgency.this);
    public final Pattern textPattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$, %, #, @, !, {,}]).+$");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_agency);
        // remove actionBar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        String[] countryOptions = {"","Palestine", "Jordan","Guinea","Sudan","Rwanda","Morocco"};
        Emailaddress = (EditText) findViewById(R.id.Emailaddress);
        AgencyName = (EditText) findViewById(R.id.AgencyName);
        Password = (EditText) findViewById(R.id.Password);
        ConfirmPassword = (EditText) findViewById(R.id.Confirmpassword);
        City = (EditText) findViewById(R.id.City);
        Phone = (EditText) findViewById(R.id.Phone);

        CountrySpinner = (Spinner) findViewById(R.id.Country);
        ArrayAdapter<String> objGenderArr = new
                ArrayAdapter<>(this, android.R.layout.simple_spinner_item, countryOptions);
        CountrySpinner.setAdapter(objGenderArr);
        CreatAgencyAccount = (Button) findViewById(R.id.CreatAgencyAccount);
        CreatAgencyAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean validEmail=validateEmail();
                boolean validPass=validatePassword();
                boolean validName=validateName();
                boolean validCity=validateCity();
                boolean validPhone=validatePhoneNumber();


                if (validEmail&&validPass&&validName&&validCity&&validPhone) {
                    encryptedPassword();
                    String encryptpass=encryptedPassword.toString();
                    Agency agency = new Agency(Emailaddress.getText().toString(), AgencyName.getText().toString(), encryptpass, CountrySpinner.getSelectedItem().toString(), City.getText().toString(), Long.parseLong(Phone.getText().toString()));
                    Boolean result = agencyDB.insertAgency(agency);
                    if(result){
                    //Toast.makeText(SignUpAgency.this, agencyDB.getAllAgencies() + "", Toast.LENGTH_SHORT).show();
                        agencyemail=Emailaddress.getText().toString();
                        sign=true;
                        Toast.makeText(SignUpAgency.this, "Registered successfully  ", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(SignUpAgency.this, HomeAgency.class);
                        startActivity(intent);
                         finish();
                    }
                }
            }
        });

// to set the number introduction for each country
       CountrySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch(position) {

                    case 1 :
                        Phone.setText("00970");
                        break;

                    case 2 :
                        Phone.setText("00962");
                        break;
                    case 3 :
                        Phone.setText("240");
                        break;
                    case 4 :
                        Phone.setText("249");
                        break;
                    case 5 :
                        Phone.setText("250");
                        break;
                    case 6 :
                        Phone.setText("212");
                        break;

                    default :
                        Phone.setText("");

                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    private boolean validateEmail() {
        String str = Emailaddress.getText().toString().trim();

        if (str.isEmpty()) {
            Emailaddress.setError("Email can not be empty, fill it");
            Emailaddress.setBackgroundColor(Color.parseColor("#DC0909"));
            return false;
        } else {
            boolean repeatedEmail = agencyDB.checkEmailAddress(Emailaddress.getText().toString());
            boolean repeatedEmail2=tenantDB.checkEmailAddress(Emailaddress.getText().toString());
            if (repeatedEmail||repeatedEmail2) {
                Emailaddress.setError("This email was token");
                Emailaddress.setBackgroundColor(Color.parseColor("#DC0909"));
                return false;
            } else {
                if (Patterns.EMAIL_ADDRESS.matcher(str).matches()) {
                    Emailaddress.setError(null);
                    Emailaddress.setBackgroundColor(Color.TRANSPARENT);
                    return true;
                } else {
                    Emailaddress.setError("Invalid email");
                    Emailaddress.setBackgroundColor(Color.parseColor("#DC0909"));
                    return false;
                }
            }
        }

    }
    private boolean validatePassword(){
        String str=Password.getText().toString().trim();
        String str2=ConfirmPassword.getText().toString().trim();

        if(str.isEmpty()){
            Password.setError("Password can not be empty, fill it");
            Password.setBackgroundColor(Color.parseColor("#DC0909"));
            return false;
        }
        else{
            if((str.length() > 7 || str.length() <= 15) && (textPattern.matcher(str).matches())){
                if(str.equals(str2)){
                    Password.setError(null);
                    Password.setBackgroundColor(Color.TRANSPARENT);
                    ConfirmPassword.setBackgroundColor(Color.TRANSPARENT);
                    return true;
                }
                else{
                    Password.setError("Passwords mismatch");
                    Password.setBackgroundColor(Color.parseColor("#DC0909"));
                    ConfirmPassword.setBackgroundColor(Color.parseColor("#DC0909"));
                    return false;
                }
            }
            else {
                Password.setError("Invalid password");
                Password.setBackgroundColor(Color.parseColor("#DC0909"));
                return false;
            }
        }
    }

    private boolean validateName(){
        String str=AgencyName.getText().toString().trim();
        if(str.isEmpty()){
            AgencyName.setError("Please fill the name");
            AgencyName.setBackgroundColor(Color.parseColor("#DC0909"));
            return false;
        }
        else{
            if(str.length() <= 20){
                AgencyName.setError(null);
                AgencyName.setBackgroundColor(Color.TRANSPARENT);
                return true;
            }
            else{
                AgencyName.setError("Maximum characters  of the name 20 only");
                AgencyName.setBackgroundColor(Color.parseColor("#DC0909"));
            }
            return false;
        }
    }




    private boolean validateCity(){
        String str=City.getText().toString().trim();
        String commaSplitter=",";
        if(str.isEmpty()){
            City.setError("Please enter two cities with ',' between them");
            City.setBackgroundColor(Color.parseColor("#DC0909"));
            return false;
        }
        else{
            if(str.contains( commaSplitter)){
                City.setError(null);
                City.setBackgroundColor(Color.TRANSPARENT);
                return true;
            }
            else{
               City.setError("Invalid city");
                City.setBackgroundColor(Color.parseColor("#DC0909"));
                return false;
            }
        }
    }
    private boolean validatePhoneNumber(){
        String str=Phone.getText().toString().trim();
        if(str.isEmpty()){
            Phone.setError("Phone number can not be empty, fill it");
            Phone.setBackgroundColor(Color.parseColor("#DC0909"));
            return false;
        }
        else{
            if(PhoneNumberUtils.isGlobalPhoneNumber(str)){
                Phone.setError(null);
                Phone.setBackgroundColor(Color.TRANSPARENT);
                return true;
            }
            else{
                Phone.setError("Invalid phone number");
                Phone.setBackgroundColor(Color.parseColor("#DC0909"));
                return false;
            }}
    }

    public void encryptedPassword(){
        MessageDigest md= null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        md.update(Password.getText().toString().getBytes());
        byte[] rba=md.digest();
        encryptedPassword=new StringBuilder();
        for(byte b:rba){
            encryptedPassword.append(String.format("%02x",b));
        }
    }
}

