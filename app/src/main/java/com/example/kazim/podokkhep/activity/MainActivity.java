package com.example.kazim.podokkhep.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kazim.podokkhep.R;
import com.example.kazim.podokkhep.adapter.EmployeeAdapter;
import com.example.kazim.podokkhep.model.Employee;
import com.example.kazim.podokkhep.model.EmployeeList;
import com.example.kazim.podokkhep.network.GetEmployeeDataService;
import com.example.kazim.podokkhep.network.RetrofitInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private EmployeeAdapter adapter;
    private RecyclerView recyclerView;

    private ProgressBar progressBar;
    private Button reloadButton;
    private TextView reloadTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progress);
        reloadButton = findViewById(R.id.btn_reload);
        reloadTextView = findViewById(R.id.text_reload);

        /*Create handle for the RetrofitInstance interface*/
        GetEmployeeDataService service = RetrofitInstance.getRetrofitInstance().create(GetEmployeeDataService.class);


        /*Call the method with parameter in the interface to get the employee data*/
        //Get Specific data of one Company . Total 3 level of specification, 1.script, 2.Parameter, 3.Parameter Value
        Call<EmployeeList> call = service.getEmployeeData(100);
        super.onCreate(savedInstanceState);
        //http://navjacinth9.000webhostapp.com/json/retrofit-demo.php?company_no=100

        /*Log the URL called*/
        Log.wtf("URL Called", call.request().url() + "");

        call.enqueue(new Callback<EmployeeList>() {
            @Override
            public void onResponse(Call<EmployeeList> call, Response<EmployeeList> response) {

                progressBar.setVisibility(View.INVISIBLE);
                generateEmployeeList(response.body().getEmployeeArrayList());

            }

            @Override
            public void onFailure(Call<EmployeeList> call, Throwable t) {

                progressBar.setVisibility(View.INVISIBLE);
                reloadTextView.setVisibility(View.VISIBLE);
                reloadButton.setVisibility(View.VISIBLE);

                reloadButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        finish();
                        overridePendingTransition(0, 0);
                        startActivity(getIntent());
                        overridePendingTransition(0, 0);

                    }
                });

            }

        });
    }

    /*Method to generate List of employees using RecyclerView with custom adapter*/
    private void generateEmployeeList(ArrayList<Employee> empDataList) {
        recyclerView = findViewById(R.id.recycler_view_employee_list);

        adapter = new EmployeeAdapter(empDataList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);

        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);
    }

}