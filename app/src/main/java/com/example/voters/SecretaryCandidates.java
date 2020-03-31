package com.example.voters;


import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class SecretaryCandidates extends AppCompatActivity {

    private DatabaseReference reference;
    private RecyclerView recyclerView;
    private ArrayList<Candidate> list;
    private ArrayList<CandidateIMage> listimage;
    private MyAdapter adapter;
    private DatabaseReference imagereference;
    FirebaseStorage storage;
    StorageReference storageReference;

    private static final String TAG = "AllCandidates";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_candidates);

        String pres = "President";

        //reference= FirebaseDatabase.getInstance().getReference().child("candidates");
        recyclerView = (RecyclerView) findViewById(R.id.myRecycler);

        //LinearLayoutManager manager = new LinearLayoutManager(this);
        //recyclerView.setLayoutManager(manager);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<Candidate>();
        listimage = new ArrayList<CandidateIMage>();

        reference = FirebaseDatabase.getInstance().getReference();

        Query secretaryquery = reference.child("candidates").orderByChild("category").equalTo("Secretary");
        secretaryquery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {

                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                        Candidate p = dataSnapshot1.getValue(Candidate.class);
                        list.add(p);
                    }
                    adapter = new MyAdapter(SecretaryCandidates.this, list);
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

//        reference.addValueEventListener(new ValueEventListener() {
//            //String userId = mDatabase.push().getKey();
//
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//
//                for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
//                    Candidate p = dataSnapshot1.getValue(Candidate.class);
//
//                    list.add(p);
//                }
//
//                adapter = new MyAdapter(AllCandidates.this, list);
//                recyclerView.setAdapter(adapter);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                Toast.makeText(AllCandidates.this, "Ooops, something went wrong!", Toast.LENGTH_SHORT).show();
//            }
//        });



    };
}
