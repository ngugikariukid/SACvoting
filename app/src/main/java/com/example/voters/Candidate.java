package com.example.voters;

import com.google.firebase.database.IgnoreExtraProperties;


@IgnoreExtraProperties
public class Candidate {

    public String CandidateEmail;
    public String firstname;
    public String lastname;
    //public String image;
    public String party;
    public String category;
    public Integer totalVotes;

    // Default constructor required for calls to
    // DataSnapshot.getValue(Candidate.class)
    public Candidate() {
    }

    public Candidate(String CandidateEmail, String firstname, String lastname, String party, String category, Integer totalVotes){
        //this.image = image;
        this.CandidateEmail = CandidateEmail;
        this.firstname = firstname;
        this.lastname = lastname;
        this.party = party;
        this.category = category;
        this.totalVotes = totalVotes;
    }

}