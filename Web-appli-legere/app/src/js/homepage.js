import { displayElement, closeElement } from "./modules/functions.js";

if(document.getElementById("displayLogInForm") != null) {
    document.getElementById("displayLogInForm").addEventListener("click", function() {
        displayElement("#logInForm", "block");
        closeElement("#picrossBox");
    });
}

if(document.getElementById("displaySignInForm") != null) {
    document.getElementById("displaySignInForm").addEventListener("click", function() {
        displayElement("#signInForm", "block");
        closeElement("#logInForm");
        closeElement("#picrossBox");
    });
}

if(document.getElementById("closeLogInForm") != null) {
    document.getElementById("closeLogInForm").addEventListener("click", function() {
        closeElement("#logInForm");
    });
}

if (document.getElementById("closeSignInForm") != null) {
    document.getElementById("closeSignInForm").addEventListener("click", function() {
        closeElement("#signInForm");
    });
}

if (document.getElementById("playRandomPuzzle") != null) {
    document.getElementById("playRandomPuzzle").addEventListener("click", function() {
        displayElement("#picrossBox", "flex");
        closeElement("#signInForm");
        closeElement("#logInForm");
    });
}

if (document.getElementById("closePuzzle") != null) {
    document.getElementById("closePuzzle").addEventListener("click", function() {
        closeElement("#picrossBox");
    });
}

const puzzlesList = document.getElementById("puzzlesList");
matrices.forEach(element => {
    const line = document.createElement("div");

    const info = document.createElement("p");
    info.innerText = element['id']+" - "+element['creator'];
    line.appendChild(info);

    const matrice = element['matrice']; 
    const playButton = document.createElement('button');
    playButton.setAttribute("type", "button");
    playButton.innerText = "Jouer ce puzzle";
    playButton.addEventListener("click", function () {
        if (document.getElementById('signInForm') != null) {closeElement("#signInForm");}
        if (document.getElementById('logInForm') != null) {closeElement("#logInForm");}

        closeElement("#picrossBox");
        displayElement("#picrossBox", "flex");
        matricePlayed = matrice;
        playMatrice(matrice);
    });
    line.appendChild(playButton);

    puzzlesList.appendChild(line);
});

