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

public class VotesAdapter extends RecyclerView.Adapter<VotesAdapter.MyViewHolder> {

    Context context;
    public boolean hasvoted;
    ArrayList <Candidate> candidates;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;


    public VotesAdapter (Context c, ArrayList<Candidate> p){
        context = c;
        candidates =p;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.vote_results_cardview, parent, false));
    }
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.name.setText(candidates.get(position).getFirstname());
        holder.party.setText(candidates.get(position).getParty());
        holder.category.setText(candidates.get(position).getCategory());
        Integer total = candidates.get(position).getTotalVotes();
        holder.totalvotes.setText("Total Votes: "+total+"");
       // holder.totalvotes.setText(candidates.get(position).getTotalVotes());
        Picasso.get().load(candidates.get(position).getImageurl()).into(holder.profilepic);
    }


    @Override
    public int getItemCount() {
        return candidates.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView name, party, category, totalvotes;
        ImageView profilepic;
        Button vote;

        public MyViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            party = (TextView) itemView.findViewById(R.id.party);
            profilepic = (ImageView) itemView.findViewById(R.id.profilepic);
            category = (TextView) itemView.findViewById(R.id.category);
            totalvotes = (TextView) itemView.findViewById(R.id.totalvotes);
        }
    }


}