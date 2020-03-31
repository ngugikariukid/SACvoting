package com.example.voters;

import android.content.ContentResolver;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;

import android.widget.ImageView;

import java.io.IOException;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class Party extends AppCompatActivity {

    private EditText party, school, member1, member2,member3;
    private Button addparty,back;
    private ProgressBar progressBar;

    private ProgressDialog progressDialog ;
    DatabaseReference mDatabase;


    private static final String TAG = "Party";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_party);

        mDatabase = FirebaseDatabase.getInstance().getReference("paries");
        party = (EditText) findViewById(R.id.partyname);
        school = (EditText) findViewById(R.id.schoolname);
        member1 = (EditText) findViewById(R.id.member1);
        member2 = (EditText) findViewById(R.id.member2);
        member3 = (EditText) findViewById(R.id.member3);
        addparty = (Button) findViewById(R.id.btn_addparty);
        back = (Button) findViewById(R.id.btn_back);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        // Assigning Id to ProgressDialog.
        progressDialog = new ProgressDialog(Party.this);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        addparty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String partyn = party.getText().toString().trim();
                final String schooln = school.getText().toString().trim();
                final String member1n = member1.getText().toString().trim();
                final String member2n = member2.getText().toString().trim();
                final String member3n = member3.getText().toString().trim();
                String userId = mDatabase.push().getKey();
                final Integer initVotes = 0;


                if (TextUtils.isEmpty(partyn)) {
                    Toast.makeText(getApplicationContext(), "Enter the Party!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(schooln)) {
                    Toast.makeText(getApplicationContext(), "Fill in the school field!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(member1n)) {
                    Toast.makeText(getApplicationContext(), "Fill in the Member 1 field!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(member2n)) {
                    Toast.makeText(getApplicationContext(), "Fill in the Member 2 field!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(member3n)) {
                    Toast.makeText(getApplicationContext(), "Fill in the Member 3 field!", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);
                //create party
                insertparty(partyn,schooln,member1n,member2n,member3n,userId, initVotes);
                finish();
            }
        });

    }
    private void insertparty(String partyname, String school, String member1, String member2, String member3, String key, Integer votes) {

        Parties p = new Parties(partyname,school,member1,member2,member3, key, votes);

        mDatabase.child(key).setValue(p);
        Toast.makeText(getApplicationContext(), "Record added!", Toast.LENGTH_SHORT).show();
    }



    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }
}
