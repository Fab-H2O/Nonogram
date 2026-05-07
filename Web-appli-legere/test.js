// contien la grille correcte
let solution = [];
// empeche de cliquer apres la victoire
let gameLocked = false;

// retourne un nombre aleatoire entre 0 et 1
function zero(max) {
    return Math.floor(Math.random() * max);
}

// compte les groupe de 1 (couper par les 0)
function hauttab(tab, size) {
    let lignes = [];
    let colonnes = [];

    // LIGNES
    for (let i = 0; i < size; i++) {
        let row = [];
        let count = 0;

        for (let j = 0; j < size; j++) {
            if (tab[i * size + j] === 1) count++;
            else if (count > 0) {
                row.push(count);
                count = 0;
            }
        }
        if (count > 0) row.push(count);
        lignes.push(row.length ? row : ["·"]);
    }

    // COLONNES
    for (let col = 0; col < size; col++) {
        let colArr = [];
        let count = 0;

        for (let row = 0; row < size; row++) {
            if (tab[row * size + col] === 1) count++;
            else if (count > 0) {
                colArr.push(count);
                count = 0;
            }
        }
        if (count > 0) colArr.push(count);
        colonnes.push(colArr.length ? colArr : ["·"]);
    }

    const ligne = document.getElementById("ligne");
    ligne.style.gridTemplateRows = `repeat(${size}, 1fr)`;
    ligne.innerHTML = lignes
        .map(row => `<div>${row.join(" ")}</div>`)
        .join("");

    const colonne = document.getElementById("colonne");
    colonne.style.gridTemplateColumns = `repeat(${size}, 1fr)`;
    colonne.innerHTML = colonnes
        .map(col => `<div>${col.join("<br>")}</div>`)
        .join("");
}

function createGrid(size) {

    // Taille des cases selon la grille
    let cellSize = 20;

    if (size === 5) cellSize = 50;
    if (size === 10) cellSize = 35;
    if (size === 20) cellSize = 22;
    if (size === 30) cellSize = 20;

    // mise a jour du css
    document.documentElement.style.setProperty("--cell", cellSize + "px");
    document.documentElement.style.setProperty("--size", size);

    // reinitialise la grille
    const container = document.getElementById("nono-stock");
    container.innerHTML = "";
    container.style.opacity = "1";

    container.style.gridTemplateColumns = `repeat(${size}, 1fr)`;
    container.style.gridTemplateRows = `repeat(${size}, 1fr)`;

    solution = [];
    gameLocked = false;

    const tab = [];

    // genere les cases
    for (let i = 0; i < size * size; i++) {
        const valeur = zero(2);
        solution.push(valeur);
        tab.push(valeur);

        const cell = document.createElement("div");
        cell.classList.add("cell");

        cell.addEventListener("click", () => {
            if (gameLocked) return;

            cell.classList.toggle("active");
            checkWin();
        });

        container.appendChild(cell);
    }

    // calcule indices
    hauttab(tab, size);
}

// verifie si le joueur a reproduit la solution
function checkWin() {
    const cells = document.querySelectorAll("#nono-stock .cell");

    for (let i = 0; i < solution.length; i++) {
        const shouldBeBlack = solution[i] === 1;
        const isBlack = cells[i].classList.contains("active");

        if (shouldBeBlack !== isBlack) {
            return;
        }
    }

    // Gagné 
    gameLocked = true;
    alert("🎉 Bravo Alex ! Tu as gagné !");
    document.getElementById("nono-stock").style.opacity = "0.5";
}

// Boutons
document.getElementById("5").onclick = () => createGrid(5);
document.getElementById("10").onclick = () => createGrid(10);
document.getElementById("20").onclick = () => createGrid(20);
document.getElementById("30").onclick = () => createGrid(30);

// Bouton Random
document.getElementById("random").onclick = () => {
    const sizes = [5, 10, 20, 30];
    const randomSize = sizes[Math.floor(Math.random() * sizes.length)];
    createGrid(randomSize);
};

createGrid(5);
