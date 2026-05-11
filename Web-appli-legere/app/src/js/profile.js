import { displayElement, closeElement, createPuzzle, newMatrice } from "./modules/functions.js";

document.getElementById("displayUpdateForm").addEventListener("click", function() {
    displayElement("#updateInfosForm", "block");
});
document.getElementById("closeUpdateForm").addEventListener("click", function() {
    closeElement("#updateInfosForm");
});

document.getElementById("displayAddPuzzleForm").addEventListener("click", function() {
    closeElement("#picrossBox");
    displayElement("#addPuzzleForm", "block");
});
document.getElementById("closeAddPuzzleForm").addEventListener("click", function() {
    closeElement("#addPuzzleForm");
});

document.getElementById("closePicrossBox").addEventListener("click", function(){
    closeElement("#picrossBox");
});

document.getElementById("createPicross").addEventListener("click", function() {
    displayElement("#picrossBox", "flex")
    createPuzzle();
});
document.getElementById("picrossBox").addEventListener("click", function(){
    console.log(JSON.stringify(newMatrice));
    document.getElementById("matrice").value = JSON.stringify(newMatrice);
});