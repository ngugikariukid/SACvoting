package com.example.voters;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class SchoolVotesAdapter extends RecyclerView.Adapter<SchoolVotesAdapter.MyViewHolder> {

    Context context;
    public boolean hasvoted;
    ArrayList <Parties> candidates;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;


    public SchoolVotesAdapter (Context c, ArrayList<Parties> p){
        context = c;
        candidates =p;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.vote_results_cardviewschools, parent, false));
    }
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.party.setText(candidates.get(position).getPartyname());
        holder.school.setText(candidates.get(position).getSchool());
        Integer totals=candidates.get(position).getTotalVotes();
        holder.totalvotes.setText("Total Votes: "+totals+"");
    }


    @Override
    public int getItemCount() {
        return candidates.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView party, school, totalvotes;
        ImageView profilepic;
        Button vote;

        public MyViewHolder(View itemView) {
            super(itemView);
            party = (TextView) itemView.findViewById(R.id.partyname);
            school = (TextView) itemView.findViewById(R.id.school);
            totalvotes = (TextView) itemView.findViewById(R.id.totalvotes);
        }
    }


}
