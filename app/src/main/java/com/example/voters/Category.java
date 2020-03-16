package com.example.voters;

import com.google.firebase.database.IgnoreExtraProperties;


@IgnoreExtraProperties
public class Category {

    public String categoryId;
    public String categoryName;

    // Default constructor required for calls to
    // DataSnapshot.getValue(Voters.class)
    public Category() {
    }

    public Category(String categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }
}
