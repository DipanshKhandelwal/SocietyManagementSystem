package com.example.dipanshkhandelwal.societymanagementsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {

    private EditText inputEmail , inputPassword , inputName;
    private Button btnSignUp ;
    private TextView btnLogin , btnReset;
    private ProgressBar progressBar;
    private FirebaseAuth auth;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseDatabase mFirebaseInstance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        auth = FirebaseAuth.getInstance();
        mFirebaseInstance = FirebaseDatabase.getInstance();
        mFirebaseDatabase = mFirebaseInstance.getReference("users");

        inputName = (EditText) findViewById(R.id.EtName);
        inputEmail = (EditText) findViewById(R.id.EtEmail);
        inputPassword = (EditText) findViewById(R.id.EtPassword);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        btnSignUp = (Button) findViewById(R.id.bSignUp);
        btnLogin = (TextView) findViewById(R.id.bLogIn);
        btnReset = (TextView) findViewById(R.id.TvReset);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpActivity.this , LoginActivity.class));
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SignUpActivity.this,"start activity to reset password", Toast.LENGTH_LONG).show();
            }
        });
        
    }
}
