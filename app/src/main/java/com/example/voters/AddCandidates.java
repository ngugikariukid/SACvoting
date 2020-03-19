package com.example.voters;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.util.UUID;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
//import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;


public class AddCandidates extends AppCompatActivity {

    private EditText email, firstname, lastname,party,category;
    private Button btn_addCandidate, btn_back, selectimage;
    private ProgressBar progressBar;
    private FirebaseAuth auth;
    private DatabaseReference mDatabase;
    private static final String TAG = "AddCandidates";

    private Button btnChoose;
    private ImageView imageView;



    private Uri filePath;

    private final int PICK_IMAGE_REQUEST = 71;

//    // view for image view
//    private ImageView imageView;
//
//    // Uri indicates, where the image will be picked from
//    private Uri filePath;
//
//    // request code
//    private final int PICK_IMAGE_REQUEST = 22;

    // instance for firebase storage and StorageReference
    FirebaseStorage storage;
    StorageReference storageReference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_candidate);

        // initialise views
        selectimage = (Button) findViewById(R.id.btnChoose);

        // get the Firebase  storage reference
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();



        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();

        btn_addCandidate = (Button) findViewById(R.id.btn_addCandidate);
        btn_back = (Button) findViewById(R.id.btn_back);

        btnChoose = (Button) findViewById(R.id.btnChoose);
        imageView = (ImageView) findViewById(R.id.imgView);

        firstname = (EditText) findViewById(R.id.firstname);
        lastname = (EditText) findViewById(R.id.lastname);
        party = (EditText) findViewById(R.id.party);
        category = (EditText) findViewById(R.id.category);
        email = (EditText) findViewById(R.id.email);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        //btnResetPassword = (Button) findViewById(R.id.btn_reset_password);
        mDatabase = FirebaseDatabase.getInstance().getReference("candidates");

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

//        selectimage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v)
//            {
//                SelectImage();
//            }
//        });

        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImage();
            }
        });

        btn_addCandidate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String fname = firstname.getText().toString().trim();
                final String lname = lastname.getText().toString().trim();
                final String eemail = email.getText().toString().trim();
                final String cat = category.getText().toString().trim();
                final String pparty = party.getText().toString().trim();
                final Integer initVotes = 0;


                if (TextUtils.isEmpty(fname)) {
                    Toast.makeText(getApplicationContext(), "Enter FirstName!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(lname)) {
                    Toast.makeText(getApplicationContext(), "Enter LastName!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(eemail)) {
                    Toast.makeText(getApplicationContext(), "Enter Email!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(cat)) {
                    Toast.makeText(getApplicationContext(), "Enter Category!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(pparty)) {
                    Toast.makeText(getApplicationContext(), "Enter Party!", Toast.LENGTH_SHORT).show();
                    return;
                }



                progressBar.setVisibility(View.VISIBLE);
                //create user
                InsertCandidate(eemail,fname,lname,pparty,cat,initVotes);

                    startActivity(new Intent(AddCandidates.this, AdminHome.class));
            }
        });

    }


private void chooseImage() {
    Intent intent = new Intent();
    intent.setType("image/*");
    intent.setAction(Intent.ACTION_GET_CONTENT);
    startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
}

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null )
        {
            filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                imageView.setImageBitmap(bitmap);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
    private void uploadImage(String UserId) {

        if(filePath != null)
        {
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading...");
            progressDialog.show();

            StorageReference ref = storageReference.child("images/"+ UserId);
            ref.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();
                            Toast.makeText(AddCandidates.this, "Uploaded", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(AddCandidates.this, "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0*taskSnapshot.getBytesTransferred()/taskSnapshot
                                    .getTotalByteCount());
                            progressDialog.setMessage("Uploaded "+(int)progress+"%");
                        }
                    });
        }
    }

    private void InsertCandidate(String CandidateEmail, String firstname, String lastname, String party, String category, Integer totalVotes) {
        String userId = mDatabase.push().getKey();
        Candidate newCandidate = new Candidate(CandidateEmail, firstname,lastname, party, category, totalVotes);
        mDatabase.child(userId).setValue(newCandidate);
        uploadImage(userId);
        Toast.makeText(getApplicationContext(), "Candidate added!", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }
}
