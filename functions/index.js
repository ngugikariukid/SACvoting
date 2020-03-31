const functions = require('firebase-functions');

// // Create and Deploy Your First Cloud Functions
// // https://firebase.google.com/docs/functions/write-firebase-functions
//
// exports.helloWorld = functions.https.onRequest((request, response) => {
//  response.send("Hello from Firebase!");
// });

exports.countVote = functions.database.ref('/votes/{uid}').onCreate((snapshot, context) => {
  let value = snapshot.val();
  let countRef = snapshot.ref.parent.parent.parent.child(`totals/${value}`);
  return countRef.transaction(function(current) {
    return (current || 0) + 1;
  })
});
