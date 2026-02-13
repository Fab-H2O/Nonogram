import { displayElement, closeElement } from "./modules/functions.js";

document.getElementById("displayUpdateForm").addEventListener("click", function() {
    displayElement("#updateInfosForm");
});
document.getElementById("closeUpdateForm").addEventListener("click", function() {
    closeElement("#updateInfosForm");
});

document.getElementById("displayAddPuzzleForm").addEventListener("click", function() {
    displayElement("#addPuzzleForm");
});
document.getElementById("closeAddPuzzleForm").addEventListener("click", function() {
    closeElement("#addPuzzleForm");
});