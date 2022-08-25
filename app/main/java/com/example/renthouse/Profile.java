package com.example.renthouse;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.telephony.PhoneNumberUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Pattern;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class Profile extends Fragment {
String email=" ";
String name=" ";
String country=" ";
String city=" ";
String phone=" ";
StringBuilder encryptedPassword;
public final Pattern textPattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$, %, #, @, !, {,}]).+$");
EditText profile_name;
EditText profile_password;
EditText profile_confirm;
Spinner profile_country;
EditText profile_city;
EditText profile_phone;
Button Edit;
String entryEmail;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view1 =inflater.inflate(R.layout.fragment_profile, container, false);
        profile_name=(EditText)view1.findViewById(R.id.profile_name);
        profile_password=(EditText)view1.findViewById(R.id.profile_password);
        profile_confirm=(EditText)view1.findViewById(R.id.profile_confirm);
        profile_city=(EditText)view1.findViewById(R.id.profile_city);
        profile_phone=(EditText)view1.findViewById(R.id.profile_phone);
        String[] countryOptions = {"","Palestine", "Jordan","Guinea","Sudan","Rwanda","Morocco"};
        profile_country = (Spinner) view1.findViewById(R.id.profile_country);
        ArrayAdapter<String> objCountryArr = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, countryOptions);
        profile_country.setAdapter(objCountryArr);
        Edit=(Button)view1.findViewById(R.id.Edit);
        //DataBaseHelper agencyDB1=new DataBaseHelper(getActivity());
       AgencyDB agencyDB1=new AgencyDB(getActivity());
        if(Entry.login){
            entryEmail=Entry.useremial;
        }
        else{
            entryEmail=SignUpAgency.agencyemail;
        }
        Cursor cursor=agencyDB1.getAllInformation(entryEmail);
        while (cursor.moveToNext()){
            email=(cursor.getString(0));
            System.out.println(email);
            name=(cursor.getString(1));
            System.out.println(name);
            country=(cursor.getString(3));
            System.out.println(country);
            city=(cursor.getString(4));
            System.out.println(city);
            phone=(cursor.getString(5));
            System.out.println(phone);
        }


        profile_name.setText(name);
        int postion=getIndex(profile_country,country);
        profile_country.setSelection(postion);
        profile_city.setText(city);
        profile_phone.setText(phone);

        Edit.setOnClickListener((view2)-> {
            boolean validPass=validatePassword();
            boolean validName=validateName();
            boolean validCity=validateCity();
            boolean validPhone=validatePhoneNumber();
            if(profile_password.getText().toString().isEmpty()&&profile_confirm.getText().toString().isEmpty()) {
                if (validName && validCity && validPhone) {
                    boolean update = agencyDB1.updateWithoutPassword(email,profile_name.getText().toString(),profile_country.getSelectedItem().toString(),profile_city.getText().toString(), Long.parseLong(profile_phone.getText().toString()));
                    System.out.println("**************************" + update);
                Cursor cursor1=agencyDB1.getAllInformation(entryEmail);
                while (cursor1.moveToNext()) {
                  String  city1=(cursor1.getString(4));
                    System.out.println("**************************"+city1);
                }
                }
            }

            else{

                validatePassword();
                encryptedPassword();

                if(validPass){
                    boolean update = agencyDB1.updateWithPassword(email,profile_name.getText().toString(),profile_country.getSelectedItem().toString(),profile_city.getText().toString(),encryptedPassword.toString(),Long.parseLong(profile_phone.getText().toString()));
                    System.out.println("**************************" + update);
                    Cursor cursor1=agencyDB1.getAllInformation(entryEmail);
                    while (cursor1.moveToNext()) {
                        String pass=(cursor1.getString(2));
                        System.out.println("**************************"+pass);}
                }
            }

        });


        return  view1;
    }

    private boolean validatePassword(){
        String str=profile_password.getText().toString().trim();
        String str2=profile_confirm.getText().toString().trim();
        if (!str.isEmpty()||!str2.isEmpty()) {
    if ((str.length() > 7 || str.length() <= 15) && (textPattern.matcher(str).matches())) {
        if (str.equals(str2)) {
            profile_password.setError(null);

            return true;
        } else {
            profile_password.setError("Passwords mismatch");

            return false;
        }
    } else {
        profile_password.setError("Invalid password");

        return false;
    }
}
else {
    return true;
}

    }

    private boolean validateName(){
        String str=profile_name.getText().toString().trim();
        if(str.isEmpty()){
            profile_name.setError("Please fill the name");

            return false;
        }
        else{
            if(str.length() <= 20){
                profile_name.setError(null);

                return true;
            }
            else{
                profile_name.setError("Maximum characters  of the name 20 only");

            }
            return false;
        }
    }



    private boolean validateCity(){
        String str=profile_city.getText().toString().trim();
        String commaSplitter=",";
        if(str.isEmpty()){
           profile_city.setError("Please enter two cities with ',' between them");

            return false;
        }
        else{
            if(str.contains( commaSplitter)){
                profile_city.setError(null);

                return true;
            }
            else{
                profile_city.setError("Invalid city");

                return false;
            }
        }
    }
    private boolean validatePhoneNumber(){
        String str=profile_phone.getText().toString().trim();
        if(str.isEmpty()){
            profile_phone.setError("Phone number can not be empty, fill it");

            return false;
        }
        else{
            if(PhoneNumberUtils.isGlobalPhoneNumber(str)){
                profile_phone.setError(null);

                return true;
            }
            else{
                profile_phone.setError("Invalid phone number");

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
        md.update(profile_password.getText().toString().getBytes());
        byte[] rba=md.digest();
        encryptedPassword=new StringBuilder();
        for(byte b:rba){
            encryptedPassword.append(String.format("%02x",b));
        }
    }
    private int getIndex(Spinner spinner, String myString){

        int index = 0;

        for (int i=0;i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).equals(myString)){
                index = i;
            }
        }
        return index;
    }
}