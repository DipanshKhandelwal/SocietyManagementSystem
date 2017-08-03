package com.example.dipanshkhandelwal.societymanagementsystem;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddServiceVisitorForm extends AppCompatActivity {

    EditText name, address, phone, type;
    String userId;
    private ImageButton create ;
    private ProgressBar progressBar;
    private FirebaseAuth auth;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_service_visitor_form);

        auth = FirebaseAuth.getInstance();
        mFirebaseInstance = FirebaseDatabase.getInstance();
        mFirebaseDatabase = mFirebaseInstance.getReference("service_visitors");

        name = (EditText) findViewById(R.id.EtSName);
        address = (EditText) findViewById(R.id.EtSAddress);
        phone = (EditText) findViewById(R.id.EtSPhone);
        type = (EditText) findViewById(R.id.EtSType);
        progressBar = (ProgressBar) findViewById(R.id.SprogressBar2);

        create = (ImageButton) findViewById(R.id.Ssave);

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String Sname = name.getText().toString().trim();
                final String Saddress = address.getText().toString().trim();
                final String Sphone = phone.getText().toString().trim();
                final String Stype = type.getText().toString().trim();

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
                if(TextUtils.isEmpty(Stype)){
                    Toast.makeText(getApplicationContext(), "Enter type of work !" ,Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                addServiceVisitor(Sname, Saddress, Sphone, Stype );
            }
        });
    }

    private void addServiceVisitor(String name, String address, String phone, String type) {
        if (TextUtils.isEmpty(userId)) {
            userId = auth.getCurrentUser().getUid();
        }

        ServiceVisitor serviceVisitor = new ServiceVisitor(name, address, phone, type );
        mFirebaseDatabase.child(name).setValue(serviceVisitor).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(AddServiceVisitorForm.this, "Service visitor Added successfully !!", Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
                startActivity(new Intent(AddServiceVisitorForm.this, GuardInfo.class));
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(AddServiceVisitorForm.this, "There was an error adding !!", Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onBackPressed(){
        startActivity(new Intent(AddServiceVisitorForm.this, GuardInfo.class));
        super.onBackPressed();
    }
}
