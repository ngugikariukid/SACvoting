package com.example.voters;

import android.content.Context;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseError;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Query;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class BusinessAdapter extends RecyclerView.Adapter<BusinessAdapter.MyViewHolder> {

    Context context;
    public boolean hasvoted=false;
    ArrayList <Parties> candidates;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;

    public String LoggedUserEmail, LoggedUserKey;

    public BusinessAdapter (Context c, ArrayList<Parties> p){
        context = c;
        candidates =p;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.cardview_schools, parent, false));
    }
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

        //Picasso.get().load(candidates.get(position).getImageurl()).into(holder.profilepic);

        holder.school.setText(candidates.get(position).getSchool());
        holder.party.setText(candidates.get(position).getPartyname());
        holder.member1.setText(candidates.get(position).getMember1());
        holder.member2.setText(candidates.get(position).getMember2());
        holder.member3.setText(candidates.get(position).getMember3());

        holder.vote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                builder1.setMessage("Are you sure? Vote cannot be undone!");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                mAuth = FirebaseAuth.getInstance();
                                String uid = mAuth.getCurrentUser().getUid();

                                LoggedUserEmail= mAuth.getCurrentUser().getEmail();
                                String candidateId = candidates.get(position).getKey();

                                String VotedParty = candidates.get(position).getSchool()+" Rep";
                                insertvote(LoggedUserEmail, VotedParty, candidateId, uid);
                                updateTotalVotes("increaseTotalVotes", candidates.get(position).getKey());

                                validate(uid, VotedParty);

                                context.startActivity(new Intent(context, Schools.class));
                            }
                        });

                builder1.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();

            }
        });

    }

    public static void insertvote(String userkey, String categ, String candId, String uid) {
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference totalVotesRef = rootRef.child("schoolsvotes").child(categ).child(candId);
        Vote vote = new Vote(userkey);
        totalVotesRef.child(uid).setValue(vote.getVoterEmail());
    }
    public static void validate(String uid, String categ){
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference totalVotesRef = rootRef.child("validate").child(uid).child(categ);
        boolean hasvoted=true;
        Validate valid = new Validate(hasvoted);
        totalVotesRef.setValue(valid);
    }

    public static void updateTotalVotes(final String operation, String key) {
        System.out.println("Inside updateTotalVotes");

        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference totalVotesRef = rootRef.child("paries").child(key).child("totalVotes");
        totalVotesRef.runTransaction(new Transaction.Handler() {
            @Override
            public Transaction.Result doTransaction(MutableData mutableData) {
                System.out.println("Inside Transactions");
                Integer votes = mutableData.getValue(Integer.class);
                if (votes == null) {
                    System.out.println("Inside first if statement = null");
                    return Transaction.success(mutableData);
                }

                if (operation.equals("increaseTotalVotes")) {
                    System.out.println("Inside update Votes by adding 1");
                    mutableData.setValue(votes + 1);
                } else if (operation.equals("decreaseTotalVotes")){
                    mutableData.setValue(votes - 1);
                }

                return Transaction.success(mutableData);
            }

            @Override
            public void onComplete(DatabaseError databaseError, boolean b, DataSnapshot dataSnapshot) {
                // Log.d(TAG, databaseError.getMessage()); //Don't ignore errors!
            }
        });
    }

    public void closehone() {
        Intent intent = new Intent(context, Home.class);
        context.startActivity(intent);
        //finish();
    }

    @Override
    public int getItemCount() {
        return candidates.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView name, party,school, member1, member2, member3;
        ImageView profilepic;
        Button vote;

        public MyViewHolder(View itemView) {
            super(itemView);

            school = (TextView) itemView.findViewById(R.id.school);
            party = (TextView) itemView.findViewById(R.id.party);
            member1=(TextView) itemView.findViewById(R.id.member1);
            member2=(TextView) itemView.findViewById(R.id.member2);
            member3=(TextView) itemView.findViewById(R.id.member3);

            profilepic = (ImageView) itemView.findViewById(R.id.profilepic);
            //category = (TextView) itemView.findViewById(R.id.category);
            vote = (Button) itemView.findViewById(R.id.vote);
        }
    }


}