package com.example.dipanshkhandelwal.societymanagementsystem;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        auth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();
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
        })
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

            default: return super.onOptionsItemSelected(item);
        }
    }

}
