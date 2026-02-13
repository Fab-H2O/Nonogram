// compte temporaire pour la 
// generation des grilles de nonograms 
// à effacer plus tard 
// 5/5 = 1 carrées de 25 carrées
// 10/10 = 4 carrées de 25 carrées donc 100 carrées
// 20/20 = 16 carrées de 25 carrées chacun donc 400 carrées
// 30/30 = 36 carrées de 25 carrées donc 900 carrées
// random/éditeur de puzzle = ???? 
// ???? = calcule a effectuer

//5
cinq = document.getElementById('5')
cinq.addEventListener ('click', () => {
    console.log("5")
    const style = document.createElement("style");
    document.getElementById("nono-stock").innerHTML = "";
    style.textContent =`
    .nono-stock {
        cursor: pointer;
        display: grid;
        grid-template-columns: repeat(5, 5fr);
        max-width: 100%;
        border: 1px solid purple;
        cursor: pointer;
        }
        `;
        document.head.appendChild(style);

        const container = document.querySelector(".nono-stock");

});

cinq.addEventListener("mouseenter", function(event) {
    event.target.style.color = "purple";
    setTimeout(function () {
        event.target.style.color = "";
    },500);
},
false,
);

//10
dix = document.getElementById('10')
dix.addEventListener ('click', () => {
    console.log("10")
        const style = document.createElement("style");
        document.getElementById("nono-stock").innerHTML = "";
    style.textContent =`
    .nono-stock {
        cursor: pointer;
        display: grid;
        grid-template-columns: repeat(10, 5fr);
        max-width: 100%;
        border: 1px solid purple;
        cursor: pointer;
        }
        `;
        document.head.appendChild(style);

        const container = document.querySelector(".nono-stock");
});

dix.addEventListener("mouseenter", function(event) {
    event.target.style.color = "purple";
    setTimeout(function () {
        event.target.style.color = "";
    },500);
},
false,
);

//20
vint = document.getElementById('20')
vint.addEventListener ('click', () => {
    console.log("20")
});

vint.addEventListener("mouseenter", function(event) {
    event.target.style.color = "purple";
    setTimeout(function () {
        event.target.style.color = "";
    },500);
},
false,
);

//30
trente = document.getElementById('30')
trente.addEventListener ('click', () => {
    console.log("30")
});

trente.addEventListener("mouseenter", function(event) {
    event.target.style.color = "purple";
    setTimeout(function () {
        event.target.style.color = "";
    },500);
},
false,
);

//random
random = document.getElementById('random')
random.addEventListener ('click', () => {
    console.log("random")
});

random.addEventListener("mouseenter", function(event) {
    event.target.style.color = "purple";
    setTimeout(function () {
        event.target.style.color = "";
    },500);
},
false,
);

//editeur
editer = document.getElementById('editeur')
editer.addEventListener ('click', () => {
    console.log("editeur")
});

editer.addEventListener("mouseenter", function(event) {
    event.target.style.color = "purple";
    setTimeout(function () {
        event.target.style.color = "";
    },500);
},
false,
);

//try again
again = document.getElementById('try')
again.addEventListener ('click', () => {
    console.log("try again")
});

again.addEventListener("mouseenter", function(event) {
    event.target.style.color = "purple";
    setTimeout(function () {
        event.target.style.color = "";
    },500);
},
false,
);