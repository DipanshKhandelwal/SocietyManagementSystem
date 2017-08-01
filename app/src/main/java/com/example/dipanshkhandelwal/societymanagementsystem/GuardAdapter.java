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

public class GuardAdapter extends ArrayAdapter<Guard>{
    public GuardAdapter(Context context, int resource, List<Guard> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        if(convertView==null){
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.guard_layout, parent ,false);
        }

        TextView name = (TextView) convertView.findViewById(R.id.guard_name);
        TextView address = (TextView) convertView.findViewById(R.id.guard_address);
        TextView phone = (TextView) convertView.findViewById(R.id.guard_phone);
        TextView working_hours = (TextView) convertView.findViewById(R.id.guard_work_time);

        Guard guard = getItem(position);

        name.setText(guard.getName());
        address.setText(guard.getAddress());
        phone.setText(guard.getPhone_number());
        working_hours.setText(guard.getWorking_hours());

        return convertView;
    }
}
