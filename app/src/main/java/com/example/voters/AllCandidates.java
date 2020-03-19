package com.example.voters;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.NonNull;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;
public class AllCandidates extends AppCompatActivity {
    private RecyclerView recyclerView;
    private DatabaseReference myref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_candidates);

        recyclerView=(RecyclerView)findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        myref= FirebaseDatabase.getInstance().getReference().child("candidates");

        FirebaseRecyclerAdapter<Candidate,CandidateViewHolder> recyclerAdapter=
                new FirebaseRecyclerAdapter<Candidate,CandidateViewHolder>(
                Candidate.class,
                R.layout.list_item,
                CandidateViewHolder.class,
                myref )
                {
            //@Override
            protected void populateViewHolder(CandidateViewHolder viewHolder, Candidate model, int position) {
                viewHolder.setFirstname(model.getFirstname());
                viewHolder.setCategory(model.getCategory());
                viewHolder.setParty(model.getParty());


               // viewHolder.setImage(model.getImage());
            }
        };
        recyclerView.setAdapter(recyclerAdapter);
    }
    public static class CandidateViewHolder extends RecyclerView.ViewHolder {
        View mView;
       // TextView textView_title;
        TextView name, pparty, ccategory;
        //TextView textView_decription;
        ImageView pic;
        public CandidateViewHolder(View itemView) {
            super(itemView);
            mView=itemView;
            name = (TextView)itemView.findViewById(R.id.name);
            pparty = (TextView) itemView.findViewById(R.id.party);
            ccategory = (TextView) itemView.findViewById(R.id.category);
            pic=(ImageView)itemView.findViewById(R.id.profilepic);
        }
        public void setFirstname(String firstname)
        {
            name.setText(firstname+"");
        }

        public void setParty(String party)
        {
            pparty.setText(party);
        }

        public void setCategory(String category)
        {
            ccategory.setText(category);
        }

//        public void setCategory(String image)
//        {
//            Picasso.with(mView.getContext())
//                    .load(image)
//                    .into(imageView);
//        }
    }
}

//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.Query;
//import com.google.firebase.database.ValueEventListener;
//
//public class AllCandidates extends AppCompatActivity {
//
//    private TextView name, category, party;
//    CardView mycard ;
//    LinearLayout main;
//    //Intent j;
//    private Button vote;
//    private ImageView ppic;
//    //private RecyclerView recyclerView;
//    private LinearLayoutManager linearLayoutManager;
//    //private FirebaseRecyclerAdapter adapter;
//    private DatabaseReference mPostReference;
//    private DatabaseReference mDatabase;
//
//    private static final String TAG = "AllCandidates";
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.all_candidates);
//
//        main = (LinearLayout) findViewById(R.id.candidate);
//
//        mDatabase = FirebaseDatabase.getInstance().getReference("candidates");
//
//        name = (TextView) findViewById(R.id.name);
//        category = (TextView) findViewById(R.id.category);
//        party = (TextView) findViewById(R.id.party);
//        ppic = (ImageView) findViewById(R.id.profilepic);
//
//        vote = (Button) findViewById(R.id.vote);
//
//        String userId = mDatabase.push().getKey();
//
//        mDatabase.child(userId).addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                int size = (int) dataSnapshot.getChildrenCount();
//                int i, k[];
//                for(i=0; i<size;i++){
//                    Candidate candidate = dataSnapshot.getValue(Candidate.class);
//                    final String fname = candidate.firstname;
//                    final String lname = candidate.lastname;
//                    final String fullname = fname + " "+ lname;
//                    name.setText(fullname);
//                    final String email = candidate.CandidateEmail;
//                    final Integer allvotes = candidate.totalVotes;
//                    category.setText(candidate.category);
//                    party.setText(candidate.party);
//                }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError error) {
//                // Failed to read value
//                Log.w(TAG, "Failed to read value.", error.toException());
//            }
//        });
//
//
//    }
//
//
//
//
//}
