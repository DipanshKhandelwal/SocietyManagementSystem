package com.example.dipanshkhandelwal.societymanagementsystem;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by DIPANSH KHANDELWAL on 02-08-2017.
 */

public class ServiceVisitorAdapter extends ArrayAdapter<ServiceVisitor>{
    public ServiceVisitorAdapter(Context context, int resource, List<ServiceVisitor> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        if(convertView==null){
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.service_visitor_layout, parent ,false);
        }

        TextView name = (TextView) convertView.findViewById(R.id.service_visitors_name);
        TextView address = (TextView) convertView.findViewById(R.id.service_visitors_address);
        TextView phone = (TextView) convertView.findViewById(R.id.service_visitors_phone);
        TextView working_hours = (TextView) convertView.findViewById(R.id.service_visitors_type);

        ServiceVisitor serviceVisitor = getItem(position);

        name.setText(serviceVisitor.getName());
        address.setText(serviceVisitor.getAddress());
        phone.setText(serviceVisitor.getPhone_number());
        working_hours.setText(serviceVisitor.getType());

        return convertView;
    }
}
///