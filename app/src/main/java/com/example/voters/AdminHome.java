package com.example.voters;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;


public class AdminHome extends AppCompatActivity {
    private FirebaseAuth auth;
    private ProgressBar progressBar;
    private Button addCandidate, vviewvotes, btnBack, btnParty, reports;
    private FirebaseAuth.AuthStateListener authListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_homepage);

        addCandidate = (Button) findViewById(R.id.btn_addsCandidate);
        vviewvotes = (Button) findViewById(R.id.viewvotes);
        btnBack = (Button) findViewById(R.id.btn_back);
        btnParty = (Button) findViewById(R.id.btn_party);
        reports = (Button) findViewById(R.id.reports);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        reports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminHome.this, Reports.class));
            }
        });

        addCandidate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminHome.this, AddCandidate.class));
                //finish();
            }
        });
        vviewvotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminHome.this, ViewVotes.class));
                //finish();
            }
        });
        btnParty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminHome.this, Party.class));
            }
        });
    }

}