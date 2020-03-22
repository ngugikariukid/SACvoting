package com.example.voters;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;
    ArrayList <Candidate> candidates;

    public MyAdapter (Context c, ArrayList<Candidate> p){
        context = c;
        candidates =p;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.cardview, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name.setText(candidates.get(position).getFirstname());
        holder.party.setText(candidates.get(position).getParty());
        holder.category.setText(candidates.get(position).getCategory());
        
    }

    @Override
    public int getItemCount() {
        return candidates.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView name, party, category;
        ImageView profilepic;
        public MyViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.name);
            party = (TextView) itemView.findViewById(R.id.party);
            profilepic = (ImageView) itemView.findViewById(R.id.profilepic);
            category = (TextView) itemView.findViewById(R.id.category);
        }
    }
}
