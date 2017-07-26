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
    int out_time;

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public int getPhone_number() {
        return phone_number;
    }

    public String getCar_number() {
        return car_number;
    }

    public int getIn_time() {
        return in_time;
    }

    public int getOut_time() {
        return out_time;
    }
}
