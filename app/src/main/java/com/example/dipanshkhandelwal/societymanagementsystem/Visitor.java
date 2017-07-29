package com.example.dipanshkhandelwal.societymanagementsystem;

/**
 * Created by DIPANSH KHANDELWAL on 26-07-2017.
 */

public class Visitor {
    String name;
    String destinaion_address;
    String phone_number;
    String purpose_to_visit;
    String address_of_visitor;
    String guard_on_duty;

    int in_time;
    int out_time;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDestinaion_address() {
        return destinaion_address;
    }

    public void setDestinaion_address(String destinaion_address) {
        this.destinaion_address = destinaion_address;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getPurpose_to_visit() {
        return purpose_to_visit;
    }

    public void setPurpose_to_visit(String purpose_to_visit) {
        this.purpose_to_visit = purpose_to_visit;
    }

    public String getAddress_of_visitor() {
        return address_of_visitor;
    }

    public void setAddress_of_visitor(String address_of_visitor) {
        this.address_of_visitor = address_of_visitor;
    }

    public String getGuard_on_duty() {
        return guard_on_duty;
    }

    public void setGuard_on_duty(String guard_on_duty) {
        this.guard_on_duty = guard_on_duty;
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
}
