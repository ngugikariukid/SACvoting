package com.example.voters;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.google.firebase.auth.FirebaseAuth;

public class ViewVotes extends AppCompatActivity {

    private EditText inputEmail, inputPassword;
    private FirebaseAuth auth;
    private ProgressBar progressBar;
    private Button president, treasurer, secretary, science, business, back;
    private FirebaseAuth.AuthStateListener authListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_votes);

        president = (Button) findViewById(R.id.presidentresults);
        treasurer = (Button) findViewById(R.id.treasurerresults);
        secretary = (Button) findViewById(R.id.secretaryresults);
        science = (Button) findViewById(R.id.science);
        business = (Button) findViewById(R.id.business);
        back = (Button) findViewById(R.id.btn_back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        president.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ViewVotes.this, PresidentResults.class));
                //finish();
            }
        });

        secretary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ViewVotes.this, SecretaryResults.class));
                //finish();
            }
        });
        treasurer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                AllCandidates a = new AllCandidates();
                startActivity(new Intent(ViewVotes.this, TreasurerResults.class));
               // finish();
            }
        });

        business.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                AllCandidates a = new AllCandidates();
                startActivity(new Intent(ViewVotes.this, BusinessResults.class));
                // finish();
            }
        });
        science.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                AllCandidates a = new AllCandidates();
                startActivity(new Intent(ViewVotes.this, ScienceResults.class));
                // finish();
            }
        });
    }

}
