package com.example.kazim.podokkhep.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kazim on 19-Feb-18.
 */

public class Employee {

    @SerializedName("name")
    private String name;
    @SerializedName("details")
    private String details;
    @SerializedName("phone")
    private String phone;

    public Employee(String name, String details, String phone) {
        this.name = name;
        this.details = details;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getdetails() {
        return details;
    }

    public void setdetails(String details) {
        this.details = details;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


}
