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


public class Home extends AppCompatActivity {

    private EditText inputEmail, inputPassword;
    private FirebaseAuth auth;
    private ProgressBar progressBar;
    private Button president, treasurer, secretary, btnback;
    private FirebaseAuth.AuthStateListener authListener;

    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
   // public static boolean hasvoted=false;


    public String LoggedUserEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.voter_home);

        president = (Button) findViewById(R.id.president);
        treasurer = (Button) findViewById(R.id.treasurer);
        secretary = (Button) findViewById(R.id.secretary);
        btnback = (Button) findViewById(R.id.btn_back);

        mAuth = FirebaseAuth.getInstance();
        String uid = mAuth.getCurrentUser().getUid();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        Query presidentquery = mDatabase.child("validate").child(uid).child("President");
        presidentquery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    president.setEnabled(false);
                    president.setText("President: Voted Already");
                }
                else{

                    president.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startActivity(new Intent(Home.this, AllCandidates.class));
                            finish();
                        }
                    });
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        Query secretarytquery = mDatabase.child("validate").child(uid).child("Secretary");
        secretarytquery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    secretary.setEnabled(false);
                    secretary.setText("Secretary: Voted Already");
                }
                else{

                    secretary.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startActivity(new Intent(Home.this, SecretaryCandidates.class));
                            finish();
                        }
                    });
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        Query treasurertquery = mDatabase.child("validate").child(uid).child("Treasurer");
        treasurertquery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    treasurer.setEnabled(false);
                    treasurer.setText("Treasurer: Voted Already");
                }
                else{
                    treasurer.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            AllCandidates a = new AllCandidates();

                            startActivity(new Intent(Home.this, TreasurerCandidates.class));
                            finish();
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