package com.example.voters;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

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
    public String highestpresident;


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

        publish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //sendEmail();
            }
        });

        reference = FirebaseDatabase.getInstance().getReference();


        DatabaseReference mDatabasePlayers = FirebaseDatabase.getInstance().getReference().child("voters");
        Query mDatabaseHighestPlayer = mDatabasePlayers.child("candidates").orderByChild("totalVotes").limitToLast(1);
        mDatabaseHighestPlayer.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot){
                for (DataSnapshot childSnapshot: dataSnapshot.getChildren()) {
                    String highestpresident = childSnapshot.getKey();
                    Toast.makeText(Reports.this,Key,Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                throw databaseError.toException(); // don't swallow errors
            }
        });















        Query presidentquery = reference.child("candidates");
        presidentquery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                        System.out.println("Total candidates: "+ Log.e(dataSnapshot1.getKey(),dataSnapshot1.getChildrenCount() + ""));

                        String totalcandidates = dataSnapshot1.getChildrenCount() + "";
                        Candidate p = dataSnapshot1.getValue(Candidate.class);
                        System.out.println("datasnapshot returns: " + dataSnapshot1.getChildren().equals("President"));
                        String userId = dataSnapshot1.getKey();
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    protected void sendEmail(String [] email) {
        Log.i("Send email", "");
        String test;
        String[] TO = {"someone@gmail.com"};
        //String[] CC = {"xyz@gmail.com"};

        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");


        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        //emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Your subject");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Email message goes here");

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
