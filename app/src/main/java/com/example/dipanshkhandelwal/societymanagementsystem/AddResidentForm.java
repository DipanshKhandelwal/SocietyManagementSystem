package com.example.dipanshkhandelwal.societymanagementsystem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

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
    }
}
