package com.example.voters;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Reports extends AppCompatActivity {

    private String totalVoters;
    private String totalCandidates;
    DatabaseReference reference;
    TextView tvoters, pres, sec, tres, bus, sci;
    Button publish;
    private String Pres, Tres, Sec, Bus, Sci;
    private Long pr, tr, sr, br, scr;
    private int tvoterss;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.reports);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tvoters = (TextView) findViewById(R.id.tvoters);
        pres = (TextView) findViewById(R.id.pres);
        sec = (TextView) findViewById(R.id.sec);
        tres = (TextView) findViewById(R.id.tres);
        bus = (TextView) findViewById(R.id.bus);
        sci = (TextView) findViewById(R.id.sci);
        publish = (Button) findViewById(R.id.publish);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);

        reference = FirebaseDatabase.getInstance().getReference();
        System.out.println("Inside reports " );

        reference.child("voters")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        tvoterss = (int) dataSnapshot.getChildrenCount();
                        tvoters.setText(tvoterss+" Voters");
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });

        Query presidentquery = reference.child("candidates").orderByChild("category").equalTo("President");
        presidentquery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                        Candidate p = dataSnapshot1.getValue(Candidate.class);


                        String firstname = dataSnapshot1.child("firstname").getValue(String.class);
                        String lastname = dataSnapshot1.child("lastname").getValue(String.class);
                        Long tvotes = dataSnapshot1.child("totalVotes").getValue(Long.class);
                        Pres = firstname + " " + lastname;

                        pres.setText(Pres+" - "+tvotes+" Votes");
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        Query secretarytquery = reference.child("candidates").orderByChild("category").equalTo("Secretary");
        secretarytquery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                        Candidate p = dataSnapshot1.getValue(Candidate.class);

                        String firstname = dataSnapshot1.child("firstname").getValue(String.class);
                        String lastname = dataSnapshot1.child("lastname").getValue(String.class);
                        Long tvotes = dataSnapshot1.child("totalVotes").getValue(Long.class);
                        sec.setText(firstname + " " + lastname+" - "+tvotes+" Votes");
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        Query trestquery = reference.child("candidates").orderByChild("category").equalTo("Treasurer");
        trestquery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                        Candidate p = dataSnapshot1.getValue(Candidate.class);

                        String firstname = dataSnapshot1.child("firstname").getValue(String.class);
                        String lastname = dataSnapshot1.child("lastname").getValue(String.class);
                        Long tvotes = dataSnapshot1.child("totalVotes").getValue(Long.class);
                        tres.setText(firstname + " " + lastname+" - "+tvotes+" Votes");
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        Query busquery = reference.child("paries").orderByChild("school").equalTo("School of Business");
        busquery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                        String party = dataSnapshot1.child("partyname").getValue(String.class);
                        Long tvotes = dataSnapshot1.child("totalVotes").getValue(Long.class);
                        bus.setText(party + " Party " + " - "+tvotes+" Votes");
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        Query sciquery = reference.child("paries").orderByChild("school").equalTo("School of Science");
        sciquery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {

                        String party = dataSnapshot1.child("partyname").getValue(String.class);
                        Long tvotes = dataSnapshot1.child("totalVotes").getValue(Long.class);
                        sci.setText(party + " Party " + " - "+tvotes+" Votes");
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        publish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data="The SAC Voting App Report 2020 \nTotal registered voters: "+tvoterss+
                        ". \n \t\t WINNERS \n President: ";
                sendEmail(data);
            }
        });



    }

    protected void sendEmail(String data) {
        Log.i("Send email", "");
        String test;
        String[] TO = {"ngugikariukid@gmail.com"};
        //String[] CC = {"xyz@gmail.com"};

        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");


        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        //emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "SAC Voting Results 2020");
        emailIntent.putExtra(Intent.EXTRA_TEXT, data);

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
            Log.i("Finished sending email", "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(Reports.this,
                    "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }
}
