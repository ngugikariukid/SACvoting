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
    private String highestpresident;


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

        reference = FirebaseDatabase.getInstance().getReference();
        System.out.println("Inside reports " );


        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference candidatesRef = rootRef.child("candidates");
        Query tatalVotesQuery = candidatesRef.orderByChild("tatalVotes").limitToLast(1);
        tatalVotesQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot){
                for (DataSnapshot childSnapshot: dataSnapshot.getChildren()) {

                    String firstname = childSnapshot.child("firstname").getValue(String.class);
                    String lastname = childSnapshot.child("lastname").getValue(String.class);
                    pres.setText(firstname + " " + lastname);

                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                throw databaseError.toException(); // don't swallow errors
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
