package com.example.voters;

import com.google.firebase.database.IgnoreExtraProperties;


@IgnoreExtraProperties
public class Votes {

    public String getVoteremail() {
        return voteremail;
    }

    public void setVoteremail(String voteremail) {
        this.voteremail = voteremail;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCandidateemail() {
        return candidateemail;
    }

    public void setCandidateemail(String candidateemail) {
        this.candidateemail = candidateemail;
    }

    public String voteremail;
    public String category;
    public String candidateemail;

    // Default constructor required for calls to
    // DataSnapshot.getValue(Votes.class)
    public Votes() {
    }

    public Votes(String voteremail, String category, String candidateemail) {

        this.voteremail = voteremail;
        this.category = category;
        this.candidateemail = candidateemail;
    }
}