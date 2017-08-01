package com.example.dipanshkhandelwal.societymanagementsystem;

/**
 * Created by DIPANSH KHANDELWAL on 01-08-2017.
 */

public class Guard {
    String name;
    String working_hours;
    String phone_number;
    String address;

    public Guard(String name, String wroking_hours, String phone_number, String address) {
        this.name = name;
        this.working_hours = wroking_hours;
        this.phone_number = phone_number;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWorking_hours() {
        return working_hours;
    }

    public void setWorking_hours(String wroking_hours) {
        this.working_hours = wroking_hours;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
