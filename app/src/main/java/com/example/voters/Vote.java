package com.example.voters;

public class Vote {

    public Vote(String voterEmail) {
        this.voterEmail = voterEmail;
    }

    public String getVoterEmail() {
        return voterEmail;
    }

    public void setVoterEmail(String voterEmail) {
        this.voterEmail = voterEmail;
    }

    private String voterEmail;

}
