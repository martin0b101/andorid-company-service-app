package com.example.compnayservice;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class CompanyAdapter extends ArrayAdapter<Company> {
    public CompanyAdapter(Context context, ArrayList<Company> compnayArrayList){
        super(context, 0, compnayArrayList);
        }


    public View getView(int position, View convertView, ViewGroup parent){
        View listItemView = convertView;


        if (listItemView == null){
        // Layout Inflater inflates each item to be displayed in GridView.
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.item_company_info, parent, false);

        }

        Company companyItem = getItem(position);
        TextView companyName = listItemView.findViewById(R.id.company_name);
        TextView typeOfWork = listItemView.findViewById(R.id.type_of_work);
        TextView workHour = listItemView.findViewById(R.id.hour_of_work);


        Button order = listItemView.findViewById(R.id.button_order);

        //
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newScreen = new Intent(getContext(), OrderServices.class).putExtra("companyName", companyItem.getCompanyName());
                newScreen.putExtra("companyId", companyItem.getCompanyId());
                getContext().startActivity(newScreen);

            }
        });

        companyName.setText(companyItem.getCompanyName());
        typeOfWork.setText(companyItem.getWork());
        //workHour.setText(customer.getTime());
        return listItemView;
        }


}

