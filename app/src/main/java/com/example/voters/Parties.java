package com.example.voters;

import android.content.Intent;

public class Parties {



    private String partyname;

    public Parties() {
    }

    private String school;
    private String member1;
    private String member2;
    private String member3;
    private String key;
    private Integer totalVotes;

    public Integer getTotalVotes() {
        return totalVotes;
    }

    public void setTotalVotes(Integer totalVotes) {
        this.totalVotes = totalVotes;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getPartyname() {
        return partyname;
    }

    public void setPartyname(String partyname) {
        this.partyname = partyname;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getMember1() {
        return member1;
    }

    public void setMember1(String member1) {
        this.member1 = member1;
    }

    public String getMember2() {
        return member2;
    }

    public void setMember2(String member2) {
        this.member2 = member2;
    }

    public String getMember3() {
        return member3;
    }

    public void setMember3(String member3) {
        this.member3 = member3;
    }

    public Parties(String partyname, String school, String member1, String member2, String member3, String key, Integer totalVotes) {
        this.partyname = partyname;
        this.school = school;
        this.member1 = member1;
        this.member2 = member2;
        this.member3 = member3;
        this.key=key;
        this.totalVotes=totalVotes;
    }






}
