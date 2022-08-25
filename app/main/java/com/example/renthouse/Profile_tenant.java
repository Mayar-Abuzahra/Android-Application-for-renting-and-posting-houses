package com.example.renthouse;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.telephony.PhoneNumberUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Pattern;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class Profile_tenant extends Fragment {
    String email=" ";
    String FirstName=" ";
    String LastName=" ";
    String Gender=" ";
    String country=" ";
    String Nationality=" ";
    String GrossMonthlySalary=" ";
    String occupation=" ";
    String familySize=" ";
    String city=" ";
    String phone=" ";
    StringBuilder encryptedPassword;
    public final Pattern textPattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[$, %, #, @, !, {,}]).+$");
    EditText profile_First_name;
    EditText profile_Last_name;
    EditText profile_password;
    EditText profile_confirm;
    EditText profile_gender;
    EditText profile_GrossMonthlySalary;
    EditText profile_occupation;
    EditText profile_familySize;
    Spinner profile_country;
    Spinner profile_nationality;
    EditText profile_city;
    EditText profile_phone;
    Button Edit;
    String entryEmail;
    TenantDB tenantDB;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Profile_tenant() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileTenant.
     */
    // TODO: Rename and change types and number of parameters
    public static Profile_tenant newInstance(String param1, String param2) {
        Profile_tenant fragment = new Profile_tenant();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view1=inflater.inflate(R.layout.fragment_profile_tenant, container, false);
        profile_First_name=(EditText)view1.findViewById(R.id.profile_First_name);
        profile_Last_name=(EditText)view1.findViewById(R.id.profile_Last_name);
        profile_gender=(EditText)view1.findViewById(R.id.profile_gender);
        profile_occupation=(EditText)view1.findViewById(R.id.profile_occupation);
        profile_password=(EditText)view1.findViewById(R.id.profile_password);
        profile_GrossMonthlySalary=(EditText)view1.findViewById(R.id.profile_GrossMonthlySalary);
        profile_familySize=(EditText)view1.findViewById(R.id.profile_familySize);
        profile_confirm=(EditText)view1.findViewById(R.id.profile_confirm);
        profile_city=(EditText)view1.findViewById(R.id.profile_city);
        profile_phone=(EditText)view1.findViewById(R.id.profile_phone);
        String[] nationality2 = {"","Palestinian", "Jordanian","Guinean","Sudanese","Rwandan","Moroccan"};
        String[] countryOptions = {"","Palestine", "Jordan","Guinea","Sudan","Rwanda","Morocco"};
        profile_country = (Spinner) view1.findViewById(R.id.profile_country);
        profile_nationality = (Spinner) view1.findViewById(R.id.profile_nationality);

        ArrayAdapter<String> objCountryArr = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, countryOptions);
        profile_country.setAdapter(objCountryArr);
        ArrayAdapter<String> objnationalityArr = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, nationality2);
        profile_nationality.setAdapter(objnationalityArr);
        Edit=(Button)view1.findViewById(R.id.Edit);
        tenantDB= new TenantDB(getActivity(),"Login.db",null,3);

        if(Entry.login2){
            entryEmail=Entry.useremial2;
        }
        else{
            entryEmail=SignUpTenant.tenantEmail;
        }
        Cursor cursor=tenantDB.getAllInformation(entryEmail);
        while (cursor.moveToNext()){
            email=(cursor.getString(0));
            System.out.println(email);
            FirstName=(cursor.getString(1));
            System.out.println(FirstName);
            LastName=(cursor.getString(2));
            System.out.println(LastName);
            Gender=(cursor.getString(3));
            System.out.println(Gender);
            Nationality=(cursor.getString(5));
            System.out.println(Nationality);
            GrossMonthlySalary=(cursor.getString(6));
            System.out.println(GrossMonthlySalary);
            occupation=(cursor.getString(7));
            System.out.println(occupation);
            familySize=(cursor.getString(8));
            System.out.println(familySize);
            country=(cursor.getString(9));
            System.out.println(country);
            city=(cursor.getString(10));
            System.out.println(city);
            phone=(cursor.getString(11));
            System.out.println(phone);
        }


        profile_First_name.setText(FirstName);
        profile_Last_name.setText(LastName);
        profile_gender.setText(Gender);
        profile_occupation.setText(occupation);
        profile_GrossMonthlySalary.setText(GrossMonthlySalary);
        profile_familySize.setText(familySize);
        int postion=getIndex(profile_country,country);
        profile_country.setSelection(postion);
        profile_city.setText(city);
        postion=getIndex(profile_nationality,Nationality);
        profile_nationality.setSelection(postion);
        profile_phone.setText(phone);

        Edit.setOnClickListener((view2)-> {
            boolean validPass=validatePassword();
            boolean validName=validateFirstName();
            boolean validName2=validateLastName();
            boolean validgms=ValidateGrossMonthlySalary();
            boolean validfs=validateFamilySize();
            boolean validCity=validateCity();
            boolean validPhone=validatePhoneNumber();
            if(profile_password.getText().toString().isEmpty()&&profile_confirm.getText().toString().isEmpty()) {
                if (validName && validName2 && validCity && validPhone && validgms && validfs) {
                    boolean update = tenantDB.updateWithoutPassword(email,profile_First_name.getText().toString(),profile_Last_name.getText().toString(),profile_nationality.getSelectedItem().toString(),Integer.parseInt(profile_GrossMonthlySalary.getText().toString()),profile_occupation.getText().toString(),Integer.parseInt(profile_familySize.getText().toString()),profile_country.getSelectedItem().toString(),profile_city.getText().toString(), profile_phone.getText().toString());
                    Cursor cursor1=tenantDB.getAllInformation(entryEmail);
                    while (cursor1.moveToNext()) {
                        String  city1=(cursor1.getString(10));
                        System.out.println(city1);
                    }
                }
            }

            else{

                validatePassword();

                MessageDigest md= null;
                try {
                    md = MessageDigest.getInstance("MD5");
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
                md.update(profile_password.getText().toString().getBytes());
                byte[] rba=md.digest();
                StringBuilder sb=new StringBuilder();
                for(byte b:rba){
                    sb.append(String.format("%02x",b));
                }

                if(validPass){
                    boolean update = tenantDB.updateWithPassword(email,profile_First_name.getText().toString(),profile_Last_name.getText().toString(),profile_nationality.getSelectedItem().toString(),Integer.parseInt(profile_GrossMonthlySalary.getText().toString()),profile_occupation.getText().toString(),Integer.parseInt(profile_familySize.getText().toString()),profile_country.getSelectedItem().toString(),profile_city.getText().toString(),sb.toString(), profile_phone.getText().toString());
                    Cursor cursor1=tenantDB.getAllInformation(entryEmail);
                    while (cursor1.moveToNext()) {
                        String  city1=(cursor1.getString(10));
                        System.out.println(city1);
                    }
                }
            }

        });
        return view1;
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

    private boolean validateFirstName(){
        String str= profile_First_name.getText().toString().trim();
        if(str.isEmpty()){
            profile_First_name.setError("First name can not be empty, fill it");
            profile_First_name.setBackgroundColor(Color.parseColor("#DC0909"));
            return false;
        }
        else{
            if(str.length() >= 3 || str.length() <= 20){
                profile_First_name.setError(null);
                return true;
            }
            else{
                profile_First_name.setError("First name must be between 3 to 20 characters only");
                profile_First_name.setBackgroundColor(Color.parseColor("#DC0909"));
            }
            return false;
        }
    }

    private boolean validateLastName(){
        String str= profile_Last_name.getText().toString().trim();
        if(str.isEmpty()){
            profile_Last_name.setError("Last name can not be empty, fill it");
            profile_Last_name.setBackgroundColor(Color.parseColor("#DC0909"));
            return false;
        }
        else{
            if(str.length() >= 3 || str.length() <= 20){
                profile_Last_name.setError(null);
                return true;
            }
            else{
                profile_Last_name.setError("Last name must be between 3 to 20 characters only");
                profile_Last_name.setBackgroundColor(Color.parseColor("#DC0909"));}
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

    private boolean validateFamilySize(){
        String str= profile_familySize.getText().toString().trim();
        int d;
        if(str.isEmpty()){
            profile_familySize.setError("Family size can not be empty, fill it");
            profile_familySize.setBackgroundColor(Color.parseColor("#DC0909"));
            return false;
        }
        else{
            try {
                d = Integer.parseInt(str);
                profile_familySize.setError(null);
                return true;
            } catch (NumberFormatException nfe) {
                profile_familySize.setError("Invalid family size");
                profile_familySize.setBackgroundColor(Color.parseColor("#DC0909"));
                return false;
            }
        }
    }

    private boolean ValidateGrossMonthlySalary(){
        String str=profile_GrossMonthlySalary.getText().toString().trim();

        if(str.isEmpty()){
            profile_GrossMonthlySalary.setError("Gross Monthly Salary can not be empty, fill it");
            profile_GrossMonthlySalary.setBackgroundColor(Color.parseColor("#DC0909"));
            return false;
        }
        else{
            try {
                int d = Integer.parseInt(str);
                profile_GrossMonthlySalary.setError(null);
                return true;
            } catch (NumberFormatException nfe) {
                profile_GrossMonthlySalary.setError("Invalid Gross Monthly Salary");
                profile_GrossMonthlySalary.setBackgroundColor(Color.parseColor("#DC0909"));
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

    /* public void encryptedPassword(){
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
     }*/
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