// compte temporaire pour la 
// generation des grilles de nonograms 
// à effacer plus tard 
// 5/5 = 1 carrées de 25 carrées
// 10/10 = 4 carrées de 25 carrées donc 100 carrées
// 20/20 = 16 carrées de 25 carrées chacun donc 400 carrées
// 30/30 = 36 carrées de 25 carrées donc 900 carrées
// random/éditeur de puzzle = ???? 
// ???? = calcule a effectuer

function zero(max) {
    return Math.floor(Math.random() * max);
}
function hauttab(t, l) {
    var un = 0;
    const z = 0;
    const tab = t;
    const number = [];
    const numberdeux = [];
    const longreur = l;

    //ligne
    for (let i = 0; i <= 25; i++) {
        valeur = tab[i];
        if (i % 5 == 0) {
            if (un != 0) {
                number.push(un);
                un = 0;
                if (i > 0 && i < 25) {
                    number.push("space");
                }
            } else if (un == 0) {
                if (i > 0 && i < 25) {
                    number.push("space");
                }
            }
        } if (valeur == 1) {
            un += 1;
        } else if (un != 0) {
            if (valeur == 0) {
                number.push(un);
                un = 0;
            } else if (i == 25) {
                number.push(un);
                un = 0;
            }
        }
    }

    //colonne
    for (let i = 0; i < 5; i++) {
        for (let j = 0 + i; j <= 25; j = j + 5) {
            valeur = tab[j];
            if (valeur == 1) {
                un++;
            } if (valeur == 0 && un != 0) {
                numberdeux.push(un);
                un = 0;
            } else if (j == 25 && un != 0 && valeur != 0) {
                numberdeux.push(un);
                un = 0;
            }

        }
        if ( un != 0) {
            numberdeux.push(un);
            un = 0;
        }
        if (i < 4) {
            numberdeux.push("space");
        }
    }

    console.log("ligne " + number);
    console.log("colonne " + numberdeux);
    return number, numberdeux;
}

//5
cinq = document.getElementById('5')
cinq.addEventListener('click', () => {
    console.log("5");

    const style = document.createElement("style");
    style.textContent = `
    #nono-stock {
        display: grid;
        grid-template-columns: repeat(5, 1fr);
        max-width: 100%;
        border: 1px solid purple;
        cursor: pointer;
    }
    `
    document.head.appendChild(style);

    const container = document.getElementById("nono-stock");
    container.innerHTML = "";

    var tab = []
    for (let i = 0; i < 25; i++) {
        const cell = document.createElement("div");
        const valeur = zero(2);
        cell.textContent = valeur;
        container.appendChild(cell);
        tab.push(valeur);
    };
    const longreur = tab.length
    console.log(tab)
    hauttab(tab, 25)
});

//10
dix = document.getElementById('10')
dix.addEventListener('click', () => {
    console.log("10")

    const style = document.createElement("style");
    style.textContent = `
    #nono-stock {
        display: grid;
        grid-template-columns: repeat(10, 1fr);
        max-width: 100%;
        border: 1px solid purple;
        cursor: pointer;
    }
    `
    document.head.appendChild(style);

    const container = document.getElementById("nono-stock");
    container.innerHTML = "";

    for (let i = 0; i < 100; i++) {
        const cell = document.createElement("div");
        cell.textContent = zero(2);
        container.appendChild(cell)
    };
});

//20
vint = document.getElementById('20')
vint.addEventListener('click', () => {
    console.log("20")

    const style = document.createElement("style");
    style.textContent = `
    #nono-stock {
        display: grid;
        grid-template-columns: repeat(20, 1fr);
        max-width: 100%;
        border: 1px solid purple;
        cursor: pointer;
    }
    `
    document.head.appendChild(style);

    const container = document.getElementById("nono-stock");
    container.innerHTML = "";
    for (let i = 0; i < 400; i++) {
        const cell = document.createElement("div");
        cell.textContent = zero(2);
        container.appendChild(cell);
    };
});

//30
trente = document.getElementById('30')
trente.addEventListener('click', () => {
    console.log("30")

    const style = document.createElement("style");
    style.textContent = `
    #nono-stock {
        display: grid;
        grid-template-columns: repeat(30, 1fr);
        max-width: 100%;
        border: 1px solid purple;
        cursor: pointer;
    }
    `
    document.head.appendChild(style);

    const container = document.getElementById("nono-stock");
    container.innerHTML = "";

    for (let i = 0; i < 900; i++) {
        const cell = document.createElement("div");
        cell.textContent = zero(2);
        container.appendChild(cell)
    };
});

//random
random = document.getElementById('random')
random.addEventListener('click', () => {
    console.log("random")

    const style = document.createElement("style");
    style.textContent = `
    #nono-stock {
        display: grid;
        grid-template-columns: repeat(10, 1fr);
        max-width: 100%;
        border: 1px solid purple;
        cursor: pointer;
    }
    `
    document.head.appendChild(style);

    const container = document.getElementById("nono-stock");
    container.innerHTML = "";

    for (let i = 0; i < 25; i++) {
        const cell = document.createElement("div");
        cell.textContent = zero(2);
        container.appendChild(cell)
    };
});

//editeur
editer = document.getElementById('editeur')
editer.addEventListener('click', () => {
    console.log("editeur")

    const style = document.createElement("style");
    style.textContent = `
    #nono-stock {
        display: grid;
        grid-template-columns: repeat(10, 1fr);
        max-width: 100%;
        border: 1px solid purple;
        cursor: pointer;
    }
    `
    document.head.appendChild(style);

    const container = document.getElementById("nono-stock");
    container.innerHTML = "";

    for (let i = 0; i < 25; i++) {
        const cell = document.createElement("div");
        cell.textContent = zero(2);
        container.appendChild(cell)
    };
});

//try again
again = document.getElementById('try')
again.addEventListener('click', () => {
    console.log("try again")

    const container = document.getElementById("nono-stock");
    container.innerHTML = "";

    for (let i = 0; i < 25; i++) {
        const cell = document.createElement("div");
        cell.textContent = zero(2);
        container.appendChild(cell)
    };
});