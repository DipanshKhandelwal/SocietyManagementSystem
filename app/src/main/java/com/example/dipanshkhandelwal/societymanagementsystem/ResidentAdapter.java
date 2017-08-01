package com.example.dipanshkhandelwal.societymanagementsystem;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by DIPANSH KHANDELWAL on 01-08-2017.
 */

public class ResidentAdapter extends ArrayAdapter<Resident>{
    public ResidentAdapter(Context context, int resource, List<Resident> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        if(convertView==null){
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.resident_layout, parent ,false);
        }

        TextView name = (TextView) convertView.findViewById(R.id.resident_name);
        TextView address = (TextView) convertView.findViewById(R.id.resident_address);
        TextView phone = (TextView) convertView.findViewById(R.id.resident_phone);
        TextView car = (TextView) convertView.findViewById(R.id.resident_car);

        Resident resident = getItem(position);

        name.setText(resident.getName());
        address.setText(resident.getAddress());
        phone.setText(resident.getPhone_number());
        car.setText(resident.getCar_number());

        return convertView;
    }
}
