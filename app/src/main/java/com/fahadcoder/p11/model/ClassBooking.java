package com.fahadcoder.p11.model;

public class ClassBooking {

    private long booking_num;
    private int park_num;
    private long id;
    private int number_of_ticket; // take from booking page
    private double total_price; // take from booking page

    //default constructor
    public ClassBooking() {
    }

    // constructor with parameter
    public ClassBooking(long booking_num, int park_num, long id, int number_of_ticket, double total_price) {
        this.booking_num = booking_num;
        this.park_num = park_num;
        this.id = id;
        this.number_of_ticket = number_of_ticket;
        this.total_price = total_price;
    }

    public long getBooking_num() {
        return booking_num;
    }

    public void setBooking_num(long booking_num) {
        this.booking_num = booking_num;
    }

    public int getPark_num() {
        return park_num;
    }

    public void setPark_num(int park_num) {
        this.park_num = park_num;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getNumber_of_ticket() {
        return number_of_ticket;
    }

    public void setNumber_of_ticket(int number_of_ticket) {
        this.number_of_ticket = number_of_ticket;
    }

    public double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }

    @Override
    public String toString() {
        return "ClassBooking{" +
                "booking_num=" + booking_num +
                ", park_num=" + park_num +
                ", id=" + id +
                ", number_of_ticket=" + number_of_ticket +
                ", total_price=" + total_price +
                '}';
    }
}
