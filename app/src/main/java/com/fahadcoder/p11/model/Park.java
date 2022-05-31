package com.fahadcoder.p11.model;

public class Park {


    private int park_num; //need in all pages
    private String location_name; // until booking page
    private String latitude; // until booking page
    private String longitude; // until booking page
    private String time; // until booking page
    private double price_of_ticket;  // until booking page
    private int discount; // until booking page
    private int tax; // until booking page
    private String phone; // until booking page
    private String description;// need in home page

    //default constructor
    public Park() {
    }

    // constructor with parameter

    public Park(int park_num, String location_name, String latitude, String longitude, String time, double price_of_ticket, int discount, int tax, String phone, String description) {
        this.park_num = park_num;
        this.location_name = location_name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.time = time;
        this.price_of_ticket = price_of_ticket;
        this.discount = discount;
        this.tax = tax;
        this.phone = phone;
        this.description = description;
    }


    //setter and getter

    public int getPark_num() {
        return park_num;
    }

    public void setPark_num(int park_num) {
        this.park_num = park_num;
    }

    public String getLocation_name() {
        return location_name;
    }

    public void setLocation_name(String location_name) {
        this.location_name = location_name;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public double getPrice_of_ticket() {
        return price_of_ticket;
    }

    public void setPrice_of_ticket(double price_of_ticket) {
        this.price_of_ticket = price_of_ticket;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getTax() {
        return tax;
    }

    public void setTax(int tax) {
        this.tax = tax;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    // function ToString


    @Override
    public String toString() {
        return "Park{" +
                "park_num=" + park_num +
                ", location_name='" + location_name + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", time='" + time + '\'' +
                ", price_of_ticket=" + price_of_ticket +
                ", discount=" + discount +
                ", tax=" + tax +
                ", phone='" + phone + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
