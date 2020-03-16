package com.example.voters;

import com.google.firebase.database.IgnoreExtraProperties;


@IgnoreExtraProperties
public class Voters {

    public String password;
    public String VoterEmail;

    // Default constructor required for calls to
    // DataSnapshot.getValue(Voters.class)
    public Voters() {
    }

    public Voters(String VoterEmail, String password) {
        this.password = password;
        this.VoterEmail = VoterEmail;
    }
}