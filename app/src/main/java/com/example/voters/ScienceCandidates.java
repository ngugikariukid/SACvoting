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

public class ScienceCandidates extends AppCompatActivity {

    private DatabaseReference reference;
    private RecyclerView recyclerView;
    private ArrayList<Parties> list;
    private BusinessAdapter adapter;
    FirebaseStorage storage;
    StorageReference storageReference;

    private static final String TAG = "ScienceCandidates";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_candidates);

        recyclerView = (RecyclerView) findViewById(R.id.myRecycler);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<Parties>();

        reference = FirebaseDatabase.getInstance().getReference();

        Query presidentquery = reference.child("paries").orderByChild("school").equalTo("School of Science");
        presidentquery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                        Parties p = dataSnapshot1.getValue(Parties.class);
                        //String userId = dataSnapshot1.getKey();
                        list.add(p);
                    }
                    adapter = new BusinessAdapter(ScienceCandidates.this, list);
                    recyclerView.setAdapter(adapter);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    };
}