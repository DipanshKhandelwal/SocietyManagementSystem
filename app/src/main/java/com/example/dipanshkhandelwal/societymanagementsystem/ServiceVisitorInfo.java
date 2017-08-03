package com.example.dipanshkhandelwal.societymanagementsystem;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ServiceVisitorInfo extends AppCompatActivity {

    FloatingActionButton add ;
    private FirebaseAuth auth;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;

    private ServiceVisitorAdapter adapter;
    private ArrayList<ServiceVisitor> service_visitors_info_list;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_visitor_info);

        auth = FirebaseAuth.getInstance();
        mFirebaseInstance = FirebaseDatabase.getInstance();
        mFirebaseDatabase = mFirebaseInstance.getReference("service_visitors");

        service_visitors_info_list = new ArrayList<>();
        listView = (ListView) findViewById(R.id.service_visitors_list);
        adapter = new ServiceVisitorAdapter(this, R.layout.service_visitor_layout, service_visitors_info_list);
        listView.setAdapter(adapter);

        add = (FloatingActionButton) findViewById(R.id.buttonSv);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ServiceVisitorInfo.this, AddServiceVisitorForm.class));
            }
        });

        mFirebaseDatabase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                service_visitors_info_list.add(dataSnapshot.getValue(ServiceVisitor.class));
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
    public void onBackPressed(){
        startActivity(new Intent(ServiceVisitorInfo.this, MainActivity.class));
        super.onBackPressed();
    }
}
