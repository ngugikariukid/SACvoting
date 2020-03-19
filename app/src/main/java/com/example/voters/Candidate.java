package com.example.voters;

import com.google.firebase.database.IgnoreExtraProperties;


@IgnoreExtraProperties
public class Candidate {

    public String CandidateEmail;
    public String firstname;
    public String lastname;
    public String party;
    public String category;
    public Integer totalVotes;

    // Default constructor required for calls to
    // DataSnapshot.getValue(Candidate.class)
    public Candidate() {
    }
    public Candidate(String CandidateEmail, String firstname, String lastname, String party, String category, Integer totalVotes) {

        this.CandidateEmail = CandidateEmail;
        this.firstname = firstname;
        this.lastname = lastname;
        this.party = party;
        this.category = category;
        this.totalVotes = totalVotes;
    }

    public String getCandidateEmail() {
        return CandidateEmail;
    }

    public void setCandidateEmail(String candidateEmail) {
        CandidateEmail = candidateEmail;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getTotalVotes() {
        return totalVotes;
    }

    public void setTotalVotes(Integer totalVotes) {
        this.totalVotes = totalVotes;
    }

}