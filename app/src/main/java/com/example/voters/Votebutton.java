package com.example.voters;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;

import static android.content.ContentValues.TAG;

public class Votebutton {

    DatabaseReference postRef;

    public void onVote (String Uid) {
        postRef.runTransaction(new Transaction.Handler() {
            @Override
            public Transaction.Result doTransaction(MutableData mutableData) {
                Candidate p = mutableData.getValue(Candidate.class);
                if (p == null) {
                    return Transaction.success(mutableData);
                }

                p.totalVotes = p.totalVotes+1;

                // Set value and report transaction success
                mutableData.setValue(p);
                return Transaction.success(mutableData);
            }

            @Override
            public void onComplete(DatabaseError databaseError, boolean b,
                                   DataSnapshot dataSnapshot) {
                // Transaction completed
                Log.d(TAG, "postTransaction:onComplete:" + databaseError);
            }
        });
    }
}


//        public void onClick(final int position){
//            postRef = FirebaseDatabase.getInstance().getReference("candidates");
//            vote.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    postRef.runTransaction(new Transaction.Handler() {
//                        @Override
//                        public Transaction.Result doTransaction(MutableData mutableData) {
//                            Candidate p = mutableData.getValue(Candidate.class);
//                            if (p == null) {
//                                return Transaction.success(mutableData);
//                            } else {
//                                int fetched = p.getTotalVotes();
//                                fetched = fetched + 1;
//
//                                String userId = postRef.push().getKey();
//                                // Set value and report transaction success
//                                postRef.child(userId).child("totalVotes").setValue(fetched);
//
//                                //mutableData.setValue(p);
//                                return Transaction.success(mutableData);
//                            }
//                        }
//                        @Override
//                        public void onComplete(DatabaseError databaseError, boolean b,
//                                               DataSnapshot dataSnapshot) {
//                            // Transaction completed
//                            Log.d(TAG, "postTransaction:onComplete:" + databaseError);
//                        }
//                    });
//                }
//
//
//            });
//        }





//Fetch the number of data items
//                    Query countVotes = postRef.child("candidates").child(userId).orderByChild("category").equalTo("Secretary");
//                    countVotes.addListenerForSingleValueEvent(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(DataSnapshot dataSnapshot) {
//                            long size = dataSnapshot.getChildrenCount();
//                            System.out.println("Total voters in Db is: "+size);
//                            //Increment it
//                            size += 1;
//                            System.out.println("Incremented size is now: "+size);
//
//                            //Update new value of votes to the database
//                            postRef2.child("candidates").child(userId).child("totalVotes").setValue(size);
//
//                        }
//                        @Override
//                        public void onCancelled(DatabaseError databaseError) {
//
//                        }
//
//
//                    });