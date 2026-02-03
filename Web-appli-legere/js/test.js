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
    style.textContent =`
    .nono-stock {
        border: 2px solid purple;
        padding: 2% 2% 2%;
        margin-right: 120%;
        margin-top: -20%;
        margin-left: -22%;
        cursor: pointer;
        }
        `;
        document.head.appendChild(style);

        const container = document.querySelector(".nono-stock");

        for (i = 0 ; i < 25; i ++){
            const carre = document.createElement("div");
            carre.textContent = "carre " + (i + 1);
            container.appendChild(carre);
        
        }

})
//10
dix = document.getElementById('10')
dix.addEventListener ('click', () => {
    console.log("10")
})
//20
vint = document.getElementById('20')
vint.addEventListener ('click', () => {
    console.log("20")
})
//30
trente = document.getElementById('30')
trente.addEventListener ('click', () => {
    console.log("30")
})
//random
random = document.getElementById('random')
random.addEventListener ('click', () => {
    console.log("random")
})
//editeur
editer = document.getElementById('editeur')
editer.addEventListener ('click', () => {
    console.log("editeur")
})
//try again
again = document.getElementById('try')
again.addEventListener ('click', () => {
    console.log("try again")
})