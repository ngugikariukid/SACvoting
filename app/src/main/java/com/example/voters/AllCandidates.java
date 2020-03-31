package com.example.voters;


import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class AllCandidates extends AppCompatActivity {

    private DatabaseReference reference;
    private RecyclerView recyclerView;
    private ArrayList<Candidate> list;
    private MyAdapter adapter;
    FirebaseStorage storage;
    StorageReference storageReference;

    private static final String TAG = "AllCandidates";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_candidates);

        //reference= FirebaseDatabase.getInstance().getReference().child("candidates");
        recyclerView = (RecyclerView) findViewById(R.id.myRecycler);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<Candidate>();

        reference = FirebaseDatabase.getInstance().getReference();

        Query presidentquery = reference.child("candidates").orderByChild("category").equalTo("President");
        presidentquery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                        Candidate p = dataSnapshot1.getValue(Candidate.class);
                        String userId = dataSnapshot1.getKey();
                        list.add(p);
                    }
                    adapter = new MyAdapter(AllCandidates.this, list);
                    recyclerView.setAdapter(adapter);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        };
    }