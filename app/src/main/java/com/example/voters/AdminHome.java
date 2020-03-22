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
    private Button addCandidate, vviewvotes;
    private FirebaseAuth.AuthStateListener authListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_homepage);

        addCandidate = (Button) findViewById(R.id.btn_addsCandidate);
        //vviewvotes = (Button) findViewById(R.id.viewvotes);

        addCandidate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminHome.this, AddCandidates.class));
            }
        });
    }

}