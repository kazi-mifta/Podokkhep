package com.example.kazim.podokkhep.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kazim.podokkhep.R;
import com.example.kazim.podokkhep.activity.DetailActivity;
import com.example.kazim.podokkhep.model.Employee;

import java.util.ArrayList;

/**
 * Created by kazim on 19-Feb-18.
 */

public class EmployeeAdapter extends RecyclerView.Adapter <EmployeeAdapter.EmployeeViewHolder>{

    /*
    * Will Contain the Objects that are Fetched From JSON Array.
    * The Type in the "<>" indicates the type of object. If I change the
    * object type here it will be changed too*/
    private ArrayList<Employee> dataList;

    // Constructor
    public EmployeeAdapter(ArrayList<Employee> dataList){
        this.dataList = dataList;
    }

    /*
    * This Will Create A View. In this Case the Card Layout. First Have to create a View.
    * Then This View will contain and Link all The XML code. After That this view
    * have to be infalted with a layout inflator. It's likea view maker.*/
    @Override
    public EmployeeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.row_employee, parent, false);
        return new EmployeeViewHolder(view);
    }

    /*
    * When Creating View this will bind The fetched data from JSON object to
    * the respected XML elements. .*/
    @Override
    public void onBindViewHolder(EmployeeViewHolder holder, int position) {
        holder.txtEmpName.setText(dataList.get(position).getName());
        holder.txtEmpdetails.setText(dataList.get(position).getdetails());
        holder.txtEmpPhone.setText("Phone : 0"+ dataList.get(position).getPhone());
    }

    //How many Objects fetched in total
    @Override
    public int getItemCount() {
        return dataList.size();
    }

    //Properties and Fileds Of Each ItemView
    class EmployeeViewHolder extends RecyclerView.ViewHolder {

        TextView txtEmpName, txtEmpdetails, txtEmpPhone;

        EmployeeViewHolder(final View itemView) {
            super(itemView);
            txtEmpName =  itemView.findViewById(R.id.txt_employee_name);
            txtEmpdetails =  itemView.findViewById(R.id.txt_employee_details);
            txtEmpPhone =  itemView.findViewById(R.id.txt_employee_phone);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    

                    String Name = dataList.get(EmployeeViewHolder.this.getLayoutPosition()).getName();
                    String Details = dataList.get(EmployeeViewHolder.this.getLayoutPosition()).getdetails();
                    String Phone = dataList.get(EmployeeViewHolder.this.getLayoutPosition()).getPhone();

                    Intent i = new Intent(itemView.getContext(), DetailActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("name",Name);
                    bundle.putString("details",Details);
                    bundle.putString("phone",Phone);
                    i.putExtras(bundle);
                    itemView.getContext().startActivity(i);


                }
            });
        }
    }

}
