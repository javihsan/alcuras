<div>Identity Platform Quickstart</div>
<div id="message">Loading...</div>
<script src="https://www.gstatic.com/firebasejs/8.0/firebase.js"></script>
<script>
  var config = {
    apiKey: "AIzaSyA-HPdxWC0MPePPK2cHO0pTskqvumv5o-k",
    authDomain: "alcurasweb.firebaseapp.com",
  };
  firebase.initializeApp(config);
</script>

<script>
  var email = "javihsan@gmail.com";
  var password = "03390339";

  firebase.auth().onAuthStateChanged(function(user) {
    if (user) {
      document.getElementById("message").innerHTML = "Welcome, " + user.email;
      firebase.auth().currentUser.getIdToken(true).then(function(idToken) {
    	  // Send token to your backend via HTTPS
    		document.location.href='/inicioExternal?token='+idToken;
    	}).catch(function(error) {
    	  // Handle error
    	});
    } else {
      document.getElementById("message").innerHTML = "No user signed in.";
    }
  });

  firebase.auth().signInWithEmailAndPassword(email, password).catch(function(error) {
    document.getElementById("message").innerHTML = error.message;
  });
</script>