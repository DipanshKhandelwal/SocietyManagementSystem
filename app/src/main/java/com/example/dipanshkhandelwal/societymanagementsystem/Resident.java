package com.example.dipanshkhandelwal.societymanagementsystem;

import java.sql.Time;

/**
 * Created by DIPANSH KHANDELWAL on 26-07-2017.
 */

public class Resident {
    String name ;
    String address ;
    int phone_number ;
    String car_number ;

    int in_time;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(int phone_number) {
        this.phone_number = phone_number;
    }

    public String getCar_number() {
        return car_number;
    }

    public void setCar_number(String car_number) {
        this.car_number = car_number;
    }

    public int getIn_time() {
        return in_time;
    }

    public void setIn_time(int in_time) {
        this.in_time = in_time;
    }

    public int getOut_time() {
        return out_time;
    }

    public void setOut_time(int out_time) {
        this.out_time = out_time;
    }

    int out_time;
}
