package com.example.voters;


import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AllCandidates extends AppCompatActivity {

    private DatabaseReference reference;
    private RecyclerView recyclerView;
    private ArrayList<Candidate> list;
    private MyAdapter adapter;

    private static final String TAG = "AllCandidates";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_candidates);

        reference= FirebaseDatabase.getInstance().getReference().child("candidates");
        recyclerView = (RecyclerView) findViewById(R.id.myRecycler);

        //LinearLayoutManager manager = new LinearLayoutManager(this);
        //recyclerView.setLayoutManager(manager);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<Candidate>();

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()){
                    Candidate p = dataSnapshot1.getValue(Candidate.class);
                    list.add(p);
                }
                adapter = new MyAdapter(AllCandidates.this, list);
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(AllCandidates.this, "Ooops, something went wrong!", Toast.LENGTH_SHORT).show();
            }
        });

        };
    }