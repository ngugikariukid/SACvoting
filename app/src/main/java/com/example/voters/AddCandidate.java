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

import androidx.appcompat.app.ActionBar;

import android.widget.ImageView;

import java.io.IOException;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
//import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AddCandidate extends AppCompatActivity {

    private EditText email, firstname, lastname,party,category;
    private Button btn_addCandidate, btn_back, selectimage;
    private ProgressBar progressBar;

    // Folder path for Firebase Storage.
    private String Storage_Path = "images/";

    // Root Database Name for Firebase Database.
    private String Database_Path = "candidates";

    // Creating URI.
    private Uri FilePathUri;
    private ProgressDialog progressDialog ;

    StorageReference storageReference;
    DatabaseReference databaseReference;



    int Image_Request_Code = 7;

    private static final String TAG = "AddCandidates";


    private Button btnChoose;
    private ImageView imageView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_candidate);


        storageReference = FirebaseStorage.getInstance().getReference();
        databaseReference = FirebaseDatabase.getInstance().getReference(Database_Path);


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
        // Assigning Id to ProgressDialog.
        progressDialog = new ProgressDialog(AddCandidate.this);


        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Creating intent.
                Intent intent = new Intent();

                // Setting intent type as image to select image from phone storage.
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Please Select Image"), Image_Request_Code);

            }
        });

        btn_addCandidate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressBar.setVisibility(View.VISIBLE);
//                //create user
//                InsertCandidate(eemail,fname,lname,pparty,cat,initVotes);

                UploadImageFileToFirebaseStorage();

                    startActivity(new Intent(AddCandidate.this, AdminHome.class));
            }
        });

    }


//    private void InsertCandidate(String CandidateEmail, String firstname, String lastname, String party, String category, Integer totalVotes) {
//        String userId = mDatabase.push().getKey();
//        Candidate newCandidate = new Candidate(CandidateEmail, firstname,lastname, party, category, totalVotes);
//        mDatabase.child(userId).setValue(newCandidate);
//        uploadImage(userId);
//        Toast.makeText(getApplicationContext(), "Candidate added!", Toast.LENGTH_SHORT).show();
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Image_Request_Code && resultCode == RESULT_OK && data != null && data.getData() != null) {

            FilePathUri = data.getData();

            try {

                // Getting selected image into Bitmap.
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), FilePathUri);

                // Setting up bitmap selected image into ImageView.
                imageView.setImageBitmap(bitmap);

                // After selecting image change choose button above text.
                btnChoose.setText("Image Selected");

            }
            catch (IOException e) {

                e.printStackTrace();
            }
        }
    }
    // Creating Method to get the selected image file Extension from File Path URI.
    public String GetFileExtension(Uri uri) {

        ContentResolver contentResolver = getContentResolver();

        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();

        // Returning the file Extension.
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri)) ;

    }

    // Creating UploadImageFileToFirebaseStorage method to upload image on storage.
    public void UploadImageFileToFirebaseStorage() {

        // Checking whether FilePathUri Is empty or not.
        if (FilePathUri != null) {

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
            // Setting progressDialog Title.
            progressDialog.setTitle("Data is Uploading...");

            // Showing progressDialog.
            progressDialog.show();

            final String userId = databaseReference.push().getKey();

            // Creating second StorageReference.
            StorageReference storageReference2nd = storageReference.child(Storage_Path + userId + "." + GetFileExtension(FilePathUri));

            // Adding addOnSuccessListener to second StorageReference.
            storageReference2nd.putFile(FilePathUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            // Getting image name from EditText and store into string variable.
                            final String TempImageName = userId+"";

                            // Hiding the progressDialog after done uploading.
                            progressDialog.dismiss();

                            // Showing toast message after done uploading.
                            Toast.makeText(getApplicationContext(), "Image Uploaded Successfully ", Toast.LENGTH_LONG).show();

                            @SuppressWarnings("VisibleForTests")
                              Candidate candidate = new Candidate(TempImageName, eemail, fname,lname, pparty, cat, initVotes );



                            //ImageUploadInfo imageUploadInfo = new ImageUploadInfo(TempImageName, taskSnapshot.getDownloadUrl().toString());

                            // Getting image upload ID.
                            String ImageUploadId = userId;

                            // Adding image upload id s child element into databaseReference.
                            databaseReference.child(ImageUploadId).setValue(candidate);
                        }
                    })
                    // If something goes wrong .
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {

                            // Hiding the progressDialog.
                            progressDialog.dismiss();

                            // Showing exception error message.
                            Toast.makeText(AddCandidate.this, exception.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    })

                    // On progress change upload time.
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {

                            // Setting progressDialog Title.
                            progressDialog.setTitle("Image is Uploading...");

                        }
                    });
        }
        else {

            Toast.makeText(AddCandidate.this, "Please Select Image or Add Image Name", Toast.LENGTH_LONG).show();

        }
    }



    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
    }
}
