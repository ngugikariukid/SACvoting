package com.example.voters;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;


public class MainPage extends AppCompatActivity {
    private FirebaseAuth auth;
    private ProgressBar progressBar;
    private Button vote, vviewvotes, btnBack;
    private FirebaseAuth.AuthStateListener authListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_voter_home);

        vote = (Button) findViewById(R.id.btn_vote);
        vviewvotes = (Button) findViewById(R.id.viewvotes);
        btnBack = (Button) findViewById(R.id.btn_back);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainPage.this, Redirect.class));
                finish();
            }
        });

        vote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainPage.this, Redirect.class));
                //finish();
            }
        });
        vviewvotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainPage.this, ViewVotes.class));
                //finish();
            }
        });
    }
}