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

public class AddGuardForm extends AppCompatActivity {

    EditText name, address, phone, work;
    String userId;
    private ImageButton create ;
    private ProgressBar progressBar;
    private FirebaseAuth auth;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_guard_form);

        auth = FirebaseAuth.getInstance();
        mFirebaseInstance = FirebaseDatabase.getInstance();
        mFirebaseDatabase = mFirebaseInstance.getReference("guards");

        name = (EditText) findViewById(R.id.EtGName);
        address = (EditText) findViewById(R.id.EtGAddress);
        phone = (EditText) findViewById(R.id.EtGPhone);
        work = (EditText) findViewById(R.id.EtGWork);
        progressBar = (ProgressBar) findViewById(R.id.GprogressBar2);

        create = (ImageButton) findViewById(R.id.Gsave);

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String Sname = name.getText().toString().trim();
                final String Saddress = address.getText().toString().trim();
                final String Sphone = phone.getText().toString().trim();
                final String Swork = work.getText().toString().trim();

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
                if(TextUtils.isEmpty(Swork)){
                    Toast.makeText(getApplicationContext(), "Enter working time !" ,Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                addGuard(Sname, Saddress, Sphone, Swork );
            }
        });
    }

    private void addGuard(String name, String address, String phone, String work) {
        if (TextUtils.isEmpty(userId)) {
            userId = auth.getCurrentUser().getUid();
        }

        Guard guard = new Guard(name, address, phone, work );
        mFirebaseDatabase.child(name).setValue(guard).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(AddGuardForm.this, "Resident Added successfully !!", Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
                startActivity(new Intent(AddGuardForm.this, GuardInfo.class));
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(AddGuardForm.this, "There was an error adding !!", Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }
}
