package com.example.renthouse;

import java.util.Arrays;
import java.util.Date;

public class House {

    private int house_id;
    private String agencyEmail;
    private String city;
    private String postal_address;
    private float surface_area;
    private long construction_year;
    private int number_of_bedrooms;
    private float rental_price;
    private boolean furnished;
    private byte[] images;
    private String availability_date;
    private String description;
    private boolean balcony;
    private boolean garden;


    public House(int house_id, String agencyEmail, String city, String postal_address, float surface_area, long construction_year, int number_of_bedrooms, float rental_price, boolean furnished, byte[] images, String availability_date, String description, boolean balcony, boolean garden) {
        this.house_id = house_id;
        this.agencyEmail = agencyEmail;
        this.city = city;
        this.postal_address = postal_address;
        this.surface_area = surface_area;
        this.construction_year = construction_year;
        this.number_of_bedrooms = number_of_bedrooms;
        this.rental_price = rental_price;
        this.furnished = furnished;
        this.images = images;
        this.availability_date = availability_date;
        this.description = description;
        this.balcony = balcony;
        this.garden = garden;
    }

    public void setHouse_id(int house_id) {
        this.house_id = house_id;
    }

    public void setAgencyEmail(String agencyEmail) {
        this.agencyEmail = agencyEmail;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setPostal_address(String postal_address) {
        this.postal_address = postal_address;
    }

    public void setSurface_area(float surface_area) {
        this.surface_area = surface_area;
    }

    public void setConstruction_year(long construction_year) {
        this.construction_year = construction_year;
    }

    public void setNumber_of_bedrooms(int number_of_bedrooms) {
        this.number_of_bedrooms = number_of_bedrooms;
    }

    public void setRental_price(float rental_price) {
        this.rental_price = rental_price;
    }

    public void setFurnished(boolean furnished) {
        this.furnished = furnished;
    }

    public void setImages(byte[] images) {
        this.images = images;
    }

    public void setAvailability_date(String availability_date) {
        this.availability_date = availability_date;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setBalcony(boolean balcony) {
        this.balcony = balcony;
    }

    public void setGarden(boolean garden) {
        this.garden = garden;
    }

    public int getHouse_id() {
        return house_id;
    }

    public String getAgencyEmail() {
        return agencyEmail;
    }

    public String getCity() {
        return city;
    }

    public String getPostal_address() {
        return postal_address;
    }

    public float getSurface_area() {
        return surface_area;
    }

    public long getConstruction_year() {
        return construction_year;
    }

    public int getNumber_of_bedrooms() {
        return number_of_bedrooms;
    }

    public float getRental_price() {
        return rental_price;
    }

    public boolean isFurnished() {
        return furnished;
    }

    public byte[] getImages() {
        return images;
    }

    public String getAvailability_date() {
        return availability_date;
    }

    public String getDescription() {
        return description;
    }

    public boolean isBalcony() {
        return balcony;
    }

    public boolean isGarden() {
        return garden;
    }

    public House(String agencyEmail, String city, String postal_address, float surface_area, long construction_year, int number_of_bedrooms, float rental_price, boolean furnished, byte[] images, String availability_date, String description, boolean balcony, boolean garden) {
        this.agencyEmail = agencyEmail;
        this.city = city;
        this.postal_address = postal_address;
        this.surface_area = surface_area;
        this.construction_year = construction_year;
        this.number_of_bedrooms = number_of_bedrooms;
        this.rental_price = rental_price;
        this.furnished = furnished;
        this.images = images;
        this.availability_date = availability_date;
        this.description = description;
        this.balcony = balcony;
        this.garden = garden;
    }

    @Override
    public String toString() {
        return "House{" +
                "house_id=" + house_id +
                ", agencyEmail='" + agencyEmail + '\'' +
                ", city='" + city + '\'' +
                ", postal_address='" + postal_address + '\'' +
                ", surface_area=" + surface_area +
                ", construction_year=" + construction_year +
                ", number_of_bedrooms=" + number_of_bedrooms +
                ", rental_price=" + rental_price +
                ", furnished=" + furnished +
                ", images=" + Arrays.toString(images) +
                ", availability_date=" + availability_date +
                ", description='" + description + '\'' +
                ", balcony=" + balcony +
                ", garden=" + garden +
                '}';
    }
}




