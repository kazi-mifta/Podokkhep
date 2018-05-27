package com.example.kazim.podokkhep.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by kazim on 19-Feb-18.
 */

public class RetrofitInstance {

    //http://navjacinth9.000webhostapp.com/json/retrofit-demo.php?company_no=100
    private static Retrofit retrofit;
    private static final String BASE_URL = "http://nsc.comeze.com/wp-content/themes/dhak2/";

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
