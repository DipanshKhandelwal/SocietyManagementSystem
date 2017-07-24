package com.example.dipanshkhandelwal.societymanagementsystem;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignUpActivity extends AppCompatActivity {

    private EditText inputEmail , inputPassword , inputName;
    String userId;
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

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String email = inputEmail.getText().toString().trim();
                String password = inputPassword.getText().toString().trim();
                final String name = inputName.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), R.string.email1, Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(getApplicationContext(), R.string.email2, Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), R.string.pwd1, Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), R.string.plen, Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(SignUpActivity.this, getString(R.string.comp) + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);

                                if (!task.isSuccessful()){
                                    Toast.makeText(SignUpActivity.this, getString(R.string.auth1) + task.getException(),
                                            Toast.LENGTH_SHORT).show();
                                }else {
                                    createUser(email, email);
                                    startActivity(new Intent(SignUpActivity.this, MainActivity.class));
                                    finish();
                                }
                            }
                        });

            }
        });
    }

    private void createUser(String name, String email) {
        if (TextUtils.isEmpty(userId)) {
            userId = auth.getCurrentUser().getUid();
        }
        User user = new User(name, email);
        FirebaseUser userd = auth.getCurrentUser();
        userd.updateProfile(new UserProfileChangeRequest.Builder()
                .setDisplayName(name).build());
        mFirebaseDatabase.child(userId).setValue(user);
        addUserChangeListener();
    }

    private void addUserChangeListener() {
        mFirebaseDatabase.child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);

                if (user == null){
                    return;
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }


    class User {
        public String name;
        public String email;

        public User() {
        }

        public User(String name, String email) {
            this.name = name;
            this.email = email;
        }

        public String getName() {
            return name;
        }

        public String getEmail() {
            return email;
        }
    }
}
