package com.example.renthouse;

public class AgencyToTenant {
    private int AgencyTOTenantID;
    private int houseID;
    private String city;
    private String postalAddress;
    private int rentingPeriod;
    private String agencyEmail;
    private String tenantEmail;
    private boolean waiting;

    public AgencyToTenant(int houseID, String city, String postalAddress, int rentingPeriod, String agencyEmail, String tenantEmail, boolean waiting) {
        this.houseID = houseID;
        this.city = city;
        this.postalAddress = postalAddress;
        this.rentingPeriod = rentingPeriod;
        this.agencyEmail = agencyEmail;
        this.tenantEmail = tenantEmail;
        this.waiting = waiting;
    }

    public AgencyToTenant(int agencyTOTenantID, int houseID, String city, String postalAddress, int rentingPeriod, String agencyEmail, String tenantEmail, boolean waiting) {
        AgencyTOTenantID = agencyTOTenantID;
        this.houseID = houseID;
        this.city = city;
        this.postalAddress = postalAddress;
        this.rentingPeriod = rentingPeriod;
        this.agencyEmail = agencyEmail;
        this.tenantEmail = tenantEmail;
        this.waiting = waiting;
    }

    public int getAgencyTOTenantID() {
        return AgencyTOTenantID;
    }

    public int getHouseID() {
        return houseID;
    }

    public String getCity() {
        return city;
    }

    public String getPostalAddress() {
        return postalAddress;
    }

    public int getRentingPeriod() {
        return rentingPeriod;
    }

    public String getAgencyEmail() {
        return agencyEmail;
    }

    public String getTenantEmail() {
        return tenantEmail;
    }

    public boolean isWaiting() {
        return waiting;
    }

    public void setAgencyTOTenantID(int agencyTOTenantID) {
        AgencyTOTenantID = agencyTOTenantID;
    }

    public void setHouseID(int houseID) {
        this.houseID = houseID;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setPostalAddress(String postalAddress) {
        this.postalAddress = postalAddress;
    }

    public void setRentingPeriod(int rentingPeriod) {
        this.rentingPeriod = rentingPeriod;
    }

    public void setAgencyEmail(String agencyEmail) {
        this.agencyEmail = agencyEmail;
    }

    public void setTenantEmail(String tenantEmail) {
        this.tenantEmail = tenantEmail;
    }

    public void setWaiting(boolean waiting) {
        this.waiting = waiting;
    }

    @Override
    public String toString() {
        return "AgencyToTenant{" +
                "AgencyTOTenantID=" + AgencyTOTenantID +
                ", houseID=" + houseID +
                ", city='" + city + '\'' +
                ", postalAddress='" + postalAddress + '\'' +
                ", rentingPeriod=" + rentingPeriod +
                ", agencyEmail='" + agencyEmail + '\'' +
                ", tenantEmail='" + tenantEmail + '\'' +
                ", waiting=" + waiting +
                '}';
    }
}
