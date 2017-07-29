package com.example.dipanshkhandelwal.societymanagementsystem;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class AddResidentsActivity extends AppCompatActivity {

    FloatingActionButton add ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_residents);

        add = (FloatingActionButton) findViewById(R.id.add_resident);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddResidentsActivity.this, AddResidentForm.class));
            }
        });
    }
}
