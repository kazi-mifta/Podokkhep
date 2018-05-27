package com.example.kazim.podokkhep.network;

import com.example.kazim.podokkhep.model.EmployeeList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by kazim on 19-Feb-18.
 */

public interface GetEmployeeDataService {

    //http://navjacinth9.000webhostapp.com/json/retrofit-demo.php?company_no=100
    @GET("login.php")//Specific PHP script
    Call<EmployeeList> getEmployeeData(@Query("company_no") int companyNo);//A specific Parameter

}
