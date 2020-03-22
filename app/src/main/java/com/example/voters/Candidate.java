package com.example.voters;

import com.google.firebase.database.IgnoreExtraProperties;


@IgnoreExtraProperties
public class Candidate {

    private String candidateemail;

    public String getCandidateemail() {
        return candidateemail;
    }

    public void setCandidateemail(String candidateemail) {
        this.candidateemail = candidateemail;
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

    private String firstname;
    private String lastname;
    private String party;
    private String category;
    private Integer totalVotes;

    // Default constructor required for calls to
    // DataSnapshot.getValue(Candidate.class)
    public Candidate() {
    }

    public Candidate(String candidateemail, String firstname, String lastname, String party, String category, Integer totalVotes) {

        this.candidateemail = candidateemail;
        this.firstname = firstname;
        this.lastname = lastname;
        this.party = party;
        this.category = category;
        this.totalVotes = totalVotes;
    }


}