package com.example.voters;

import com.google.firebase.database.IgnoreExtraProperties;


@IgnoreExtraProperties
public class Votes {

    public String voteLogId;
    public String VoterEmail;
    public String categoryId;
    public String CandidateEmail;

    // Default constructor required for calls to
    // DataSnapshot.getValue(Votes.class)
    public Votes() {
    }

    public Votes(String voteLogId, String VoterEmail, String categoryId, String candidateEmail) {
        this.voteLogId = voteLogId;
        this.VoterEmail = VoterEmail;
        this.categoryId = categoryId;
        this.CandidateEmail = candidateEmail;
    }
}