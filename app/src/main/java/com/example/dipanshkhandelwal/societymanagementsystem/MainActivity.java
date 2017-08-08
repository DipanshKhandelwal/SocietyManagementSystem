package com.example.dipanshkhandelwal.societymanagementsystem;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.security.PublicKey;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static android.R.attr.entries;
import static android.R.attr.x;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton add;
    ImageButton Allow, Reset, Info, OnCar, OnFoot;
    EditText name;
    TextView TvName,TvAddress, TvPhone, TvCar, TvOut, TvIn, TvDuration;
    private FirebaseAuth auth;
    private DatabaseReference mFirebaseDatabaseResidentList;
    private DatabaseReference mFirebaseDatabaseEntries;
    private FirebaseDatabase mFirebaseInstance;
    private List<Resident> Residents= new ArrayList<>();
    private LinearLayout ResidentLayout, VisitorLayout;
    private Boolean Resident;
    private String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        auth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_main);

        mFirebaseInstance = FirebaseDatabase.getInstance();
        mFirebaseDatabaseResidentList = mFirebaseInstance.getReference("residents");
        mFirebaseDatabaseEntries = mFirebaseInstance.getReference("entries");

        add = (FloatingActionButton) findViewById(R.id.new_resident);
        Allow = (ImageButton) findViewById(R.id.bAllow);
        Reset = (ImageButton) findViewById(R.id.bReset);
        Info = (ImageButton) findViewById(R.id.bInfo);
        OnCar = (ImageButton) findViewById(R.id.bOnCar);
        OnFoot = (ImageButton) findViewById(R.id.bOnFoot);

        TvName = (TextView) findViewById(R.id.TvName);
        TvAddress = (TextView) findViewById(R.id.TvAddress);
        TvPhone = (TextView) findViewById(R.id.TvPhone);
        TvCar = (TextView) findViewById(R.id.TvCar);
        TvOut = (TextView) findViewById(R.id.TvOut);
        TvIn = (TextView) findViewById(R.id.TvIn);
        TvDuration = (TextView) findViewById(R.id.TvDuration);

        ResidentLayout = (LinearLayout) findViewById(R.id.ResidentLayout);
        VisitorLayout = (LinearLayout) findViewById(R.id.VisitorLayout);

        name = (EditText) findViewById(R.id.FootOrCar);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AddResidentsActivity.class));
            }
        });

        Reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name.setText("");
                TvName.setText("");
                TvAddress.setText("");
                TvPhone.setText("");
                TvCar.setText("");
                TvOut.setText("");
                TvIn.setText("");
                TvDuration.setText("");
                name.setEnabled(true);
                Allow.setEnabled(false);
            }
        });

        Info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean isResident = false;
                Resident resident = new Resident();
                for(Resident x:Residents){
                    if(name.getText().toString().trim().equals(x.name)){
                        resident = x;
                        isResident = true;
                        break;
                    }
                }

                name.setEnabled(false);

                if(isResident){
                    Toast.makeText(MainActivity.this,"Resident present in database",Toast.LENGTH_SHORT).show();
                    Resident = true;
                    updateResidentLayout(resident);
                    Allow.setEnabled(true);
                }else{
                    Toast.makeText(MainActivity.this,"Resident isn't present in database",Toast.LENGTH_SHORT).show();
                    Resident = false;
                    //updateVisitorLayout();
                    Allow.setEnabled(true);
                }
            }
        });

        Allow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Allow.setEnabled(false);
                if(Resident){
                    Resident resident = new Resident(TvName.getText().toString(), TvAddress.getText().toString(), TvPhone.getText().toString(),TvCar.getText().toString(), TvIn.getText().toString(), TvOut.getText().toString());
                    if(!TvIn.getText().toString().equals("0")){
                        mFirebaseDatabaseEntries.child(name.getText().toString()).child(key).child("in_time").setValue(TvIn.getText().toString());
                    }else{
                        mFirebaseDatabaseEntries.child(name.getText().toString()).push().setValue(resident).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(MainActivity.this, "Resident Added successfully !!", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(MainActivity.this, "There was an error adding !!", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }else{

                }
            }
        });

        mFirebaseDatabaseResidentList.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Residents.add(dataSnapshot.getValue(Resident.class));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void updateResidentLayout(final Resident resident) {

        DatabaseReference data = mFirebaseDatabaseEntries.child(resident.getName());
        data.orderByKey().limitToLast(1).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Resident resident2 = dataSnapshot.getValue(Resident.class);

                if(resident2 != null){
                    Log.e("yoooo","not null");
                    TvName.setText(resident2.getName());
                    TvAddress.setText(resident2.getAddress());
                    TvCar.setText(resident2.getCar_number());
                    TvPhone.setText(resident2.getPhone_number());

                    String currentDateAndTime = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());

                    if(resident2.getIn_time().equals("0")){
                        Toast.makeText(MainActivity.this,"resident coming back",Toast.LENGTH_SHORT).show();
                        TvOut.setText(resident2.getOut_time());
                        TvIn.setText(currentDateAndTime);
                        key=dataSnapshot.getKey();
                    }else{
                        Toast.makeText(MainActivity.this,"resident going out",Toast.LENGTH_SHORT).show();
                        TvOut.setText(currentDateAndTime);
                        TvIn.setText("0");
                    }
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inf = getMenuInflater();
        inf.inflate(R.menu.main_menu , menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.sign_out_menu://signouts
                auth.signOut();
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                return true;
            case R.id.guard_info://signouts
                startActivity(new Intent(MainActivity.this, GuardInfo.class));
                return true;
            case R.id.service_visitor_info://signouts
                startActivity(new Intent(MainActivity.this, ServiceVisitorInfo.class));
                return true;

            default: return super.onOptionsItemSelected(item);
        }
    }

}
