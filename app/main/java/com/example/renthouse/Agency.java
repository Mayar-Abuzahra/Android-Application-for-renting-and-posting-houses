package com.example.renthouse;

public class Agency {


    private String emailAddress; // primary key
    private String agencyName;
    private String password;
    private String country;
    private String city;
    private long phone;

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getAgencyName() {
        return agencyName;
    }

    public String getPassword() {
        return password;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public long getPhone() {
        return phone;
    }

    public Agency(String emailAddress, String agencyName, String password, String country, String city, long phone) {
        this.emailAddress = emailAddress;
        this.agencyName = agencyName;
        this.password = password;
        this.country = country;
        this.city = city;
        this.phone = phone;
    }
    @Override
    public String toString() {
        return "Agency{" +
                "emailAddress='" + emailAddress + '\'' +
                ", agencyName='" + agencyName + '\'' +
                ", password='" + password + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", phone=" + phone +
                '}';
    }
}
