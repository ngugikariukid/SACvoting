package com.example.voters;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


public class Schools extends AppCompatActivity {
    private FirebaseAuth auth;
    private ProgressBar progressBar;
    private Button science, business, btnback;
    private FirebaseAuth.AuthStateListener authListener;

    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_school);

        science = (Button) findViewById(R.id.science);
        business = (Button) findViewById(R.id.business);
        btnback = (Button) findViewById(R.id.btn_back);

        mAuth = FirebaseAuth.getInstance();
        String uid = mAuth.getCurrentUser().getUid();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        Query sciencequery = mDatabase.child("validate").child(uid).child("School of Science Rep");
        sciencequery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {

                    science.setEnabled(false);
                    science.setText("School of Science Rep: Voted Already");
                }
                else{

                    science.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startActivity(new Intent(Schools.this, ScienceCandidates.class));
                            //finish();
                        }
                    });
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        Query businessquery = mDatabase.child("validate").child(uid).child("School of Business Rep");
        businessquery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    business.setEnabled(false);
                    business.setText("School of Business Rep: Voted Already");
                }
                else{
                    business.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startActivity(new Intent(Schools.this, BusinessCandidates.class));
                            //finish();
                        }
                    });
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });





    }



}