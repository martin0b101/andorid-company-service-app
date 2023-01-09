package com.example.compnayservice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.firebase.database.annotations.NotNull;

import java.util.ArrayList;

public class CustomerAdapter extends ArrayAdapter<Customer> {
    public CustomerAdapter(Context context, ArrayList<Customer> customersArrayList){
        super(context, 0, customersArrayList);
    }


    public View getView(int position, View convertView, ViewGroup parent){
        View listItemView = convertView;
        if (listItemView == null){
            // Layout Inflater inflates each item to be displayed in GridView.
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.customer_information, parent, false);

        }

        Customer customer = getItem(position);
        TextView customerName = listItemView.findViewById(R.id.customer_name);
        TextView typeOfWork = listItemView.findViewById(R.id.type_of_work);
        TextView workHour = listItemView.findViewById(R.id.hour_of_work);
        customerName.setText(customer.getCustomerName());
        typeOfWork.setText(customer.getTypeOfWork());
        workHour.setText(customer.getTime());
        return listItemView;
    }


}
