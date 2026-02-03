import { displayElement, closeElement } from "./modules/functions.js";

document.getElementById("displayLogInForm").addEventListener("click", function() {
    displayElement("#logInForm");
});
document.getElementById("displaySignInForm").addEventListener("click", function() {
    displayElement("#signInForm");
});
document.getElementById("closeLogInForm").addEventListener("click", function() {
    closeElement("#logInForm");
});
document.getElementById("closeSignInForm").addEventListener("click", function() {
    closeElement("#signInForm");
});