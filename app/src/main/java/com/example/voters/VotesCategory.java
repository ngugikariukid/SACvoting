package com.example.voters;

public class VotesCategory {
    public String getVoterId() {
        return voterId;
    }

    public void setVoterId(String voterId) {
        this.voterId = voterId;
    }

    public VotesCategory(String voterId) {
        this.voterId = voterId;
    }

    private String voterId;
}
