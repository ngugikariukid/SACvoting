package com.example.voters;

import com.google.firebase.database.IgnoreExtraProperties;


@IgnoreExtraProperties
public class Candidate {

    public String CandidateEmail;
    public String firstname;
    public String lastname;
    public String image;
    public String party;
    public String categoryId;
    public String totalVotes;

    // Default constructor required for calls to
    // DataSnapshot.getValue(Candidate.class)
    public Candidate() {
    }

    public Candidate(String image, String CandidateEmail, String firstname, String lastname, String party, String categoryId, String totalVotes){
        this.image = image;
        this.CandidateEmail = CandidateEmail;
        this.firstname = firstname;
        this.lastname = lastname;
        this.party = party;
        this.categoryId = categoryId;
        this.totalVotes = totalVotes;
    }

}