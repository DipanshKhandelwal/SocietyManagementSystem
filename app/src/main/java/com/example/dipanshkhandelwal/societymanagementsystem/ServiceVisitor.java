package com.example.dipanshkhandelwal.societymanagementsystem;

/**
 * Created by DIPANSH KHANDELWAL on 02-08-2017.
 */

public class ServiceVisitor {
    String name;
    String type;
    String phone_number;
    String address;

    public ServiceVisitor(String name, String type, String phone_number, String address) {
        this.name = name;
        this.type = type;
        this.phone_number = phone_number;
        this.address = address;
    }

    public ServiceVisitor() {
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
