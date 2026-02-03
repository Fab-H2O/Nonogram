import { displayElement, closeElement } from "./modules/functions.js";

document.getElementById("displayUpdateForm").addEventListener("click", function() {
    displayElement("#updateInfosForm");
});
document.getElementById("closeUpdateForm").addEventListener("click", function() {
    closeElement("#updateInfosForm");
});