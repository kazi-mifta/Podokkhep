package com.example.kazim.podokkhep.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by kazim on 19-Feb-18.
 */

public class EmployeeList {

    @SerializedName("employeeList")
    private ArrayList<Employee> employeeList;

    public ArrayList<Employee> getEmployeeArrayList() {
        return employeeList;
    }

    public void setEmployeeArrayList(ArrayList<Employee> employeeArrayList) {
        this.employeeList = employeeArrayList;
    }

}
