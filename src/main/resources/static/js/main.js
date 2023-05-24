document.addEventListener("DOMContentLoaded", function() {
    var userEmail = getUserEmail();
    var helloMessage = "Hello, " + userEmail;
    document.getElementById("userEmail").textContent = userEmail;
    document.getElementById("helloMessage").textContent = helloMessage;
});
