package com.example.dipanshkhandelwal.societymanagementsystem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddResidentForm extends AppCompatActivity {

    EditText name, address, phone, car;
    String userId;
    private Button create ;
    private ProgressBar progressBar;
    private FirebaseAuth auth;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_resident_form);

        auth = FirebaseAuth.getInstance();
        mFirebaseInstance = FirebaseDatabase.getInstance();
        mFirebaseDatabase = mFirebaseInstance.getReference("residents");

        name = (EditText) findViewById(R.id.EtName);
        address = (EditText) findViewById(R.id.EtAddress);
        phone = (EditText) findViewById(R.id.EtPhone);
        car = (EditText) findViewById(R.id.EtCar);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        create = (Button) findViewById(R.id.save);

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String Sname = name.getText().toString().trim();
                final String Saddress = address.getText().toString().trim();
                final String Sphone = phone.getText().toString().trim();
                final String Scar = car.getText().toString().trim();

                if(TextUtils.isEmpty(Sname)){
                    Toast.makeText(getApplicationContext(), "Enter name !" ,Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(Saddress)){
                    Toast.makeText(getApplicationContext(), "Enter address !" ,Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(Sphone)){
                    Toast.makeText(getApplicationContext(), "Enter phone number !" ,Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(Scar)){
                    Toast.makeText(getApplicationContext(), "Enter car number !" ,Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);
            }
        });
    }
}
