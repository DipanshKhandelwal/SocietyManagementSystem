package com.example.dipanshkhandelwal.societymanagementsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private EditText inputEmail , inputPassword;
    private Button btnLogin ;
    private TextView btnSignUp , btnReset;
    private FirebaseAuth auth;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        inputEmail = (EditText) findViewById(R.id.EtEmail);
        inputPassword = (EditText) findViewById(R.id.EtPassword);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        btnSignUp = (TextView) findViewById(R.id.TvSignUp);
        btnLogin = (Button) findViewById(R.id.bLogIn);
        btnReset = (TextView) findViewById(R.id.TvReset);
    }
}
