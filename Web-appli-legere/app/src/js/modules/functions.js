let cellSize = 30;

export function displayElement(id, display)
{
    const element = document.querySelector(id)
    element.style.display = display;
}
export function closeElement(id)
{
    const element = document.querySelector(id)
    element.style.display = "none";
}

export function playMatrice(matrice) {
    const inputMatrice = JSON.parse(matrice);
    gameMatrice = matrice;
    const box = document.getElementById("picrossBox");
    while (box.firstChild) {
        box.removeChild(box.lastChild);
    }
    
    const boxHeadline = document.createElement("div");
    boxHeadline.setAttribute("class", "boxHeadline");
    const closeButton = document.createElement("button");
    closeButton.setAttribute("type", "button");
    closeButton.setAttribute("class", "closeButton");
    closeButton.innerText = "✖";
    closeButton.addEventListener("click", function(){
        box.style.display = "none";
    });
    boxHeadline.appendChild(closeButton);
    box.appendChild(boxHeadline);

    const boxTop = document.createElement("div");
    boxTop.setAttribute("id", "boxTop");
    box.appendChild(boxTop);
    const boxBottom = document.createElement("div");
    boxBottom.setAttribute("id", "boxBottom");
    box.appendChild(boxBottom);
    
    const colsClues = document.createElement("div");

    colsClues.setAttribute("id", "colsClues");
    for (let i = 0; i < inputMatrice[0].length; i++) {
        const colTmp = [];
        for (let j = 0; j < inputMatrice.length; j++) {
            colTmp.push(inputMatrice[j][i]);
        }
        const col = calculeClues(colTmp);
        const clues = document.createElement("span");
        clues.setAttribute("class", "colClues");
        col.forEach(element => {
            clues.innerHTML += " "+element+"<br>";
        });
        colsClues.appendChild(clues);
    }
    boxTop.appendChild(colsClues);
    
    const rowsClues = document.createElement("div");
    var widthRowClues = 0;
    rowsClues.setAttribute("id", "rowsClues");
    for (let i = 0; i < inputMatrice.length; i++) {
        const row = calculeClues(inputMatrice[i]);
        const clues = document.createElement("span");
        clues.setAttribute("class", "rowClues");
        row.forEach(element => {
            clues.innerHTML += " "+element+" ";
        });
        rowsClues.appendChild(clues);
        console.log(clues.scrollWidth);
        if(clues.scrollWidth > widthRowClues) {
            widthRowClues = clues.scrollWidth;
        }
    }
    boxBottom.appendChild(rowsClues);
    
    const sizeX = inputMatrice[0].length;
    const sizeY = inputMatrice.length;
    drawGrid(sizeX, sizeY);
    
    for (let i = 0; i < sizeY; i++) {
        const line = []
        gameMatrice.push(line);
    }
    for (let i = 0; i < sizeX; i++) {
        for (let j = 0; j < sizeY; j++) {
            gameMatrice[j].push(0);
        }   
    }

    const canvas = document.getElementById("picross");
    canvas.addEventListener("click", function (e) {
        getClick(gameMatrice, canvas, e);
    });

    const width = rowsClues.scrollWidth + canvas.scrollWidth + 20;
    box.style.width = ""+width+"px";
}

export function drawGrid(sizeX, sizeY) {
    const boxBottom = document.getElementById("boxBottom");

    if( document.getElementById("picross")) {
        document.getElementById("picross").remove();
    }

    const canvas = document.createElement("canvas");
    canvas.setAttribute("width", sizeX*cellSize);
    canvas.setAttribute("height", sizeY*cellSize);
    canvas.setAttribute("id", "picross");
    boxBottom.appendChild(canvas);
    
    // dessine la grille
    var ctx = canvas.getContext("2d");
    ctx.beginPath();
    ctx.strokeStyle = "grey";

    for (let i = 0; i <= sizeY; i++) {
        ctx.moveTo(0, cellSize * i);
        ctx.lineTo(sizeX*cellSize, cellSize * i);
        ctx.stroke();
    }
    for (let i = 0; i <= sizeX; i++) {
        ctx.moveTo(cellSize * i, 0);
        ctx.lineTo(cellSize * i, sizeY*cellSize);
        ctx.stroke();
    }
    ctx.closePath();
}

export function getClick(matrice, canvas, event) {
    let rect = canvas.getBoundingClientRect();
    var ctx = canvas.getContext("2d");
    let x = Math.floor((event.clientX - rect.left) / cellSize);
    let y = Math.floor((event.clientY - rect.top) / cellSize);
    if(matrice[y][x] == 0) {
        matrice[y][x] = 1;
        ctx.clearRect(x*cellSize+2, y*cellSize+2, cellSize-4, cellSize-4);
        ctx.fillStyle = "#111";
        ctx.fillRect(x*cellSize+2, y*cellSize+2, cellSize-4, cellSize-4);

    } else if (matrice[y][x] == 1) {
        matrice[y][x] = 0;
        ctx.clearRect(x*cellSize+2, y*cellSize+2, cellSize-4, cellSize-4);
        ctx.fillStyle = "#fff";
        ctx.fillRect(x*cellSize+2, y*cellSize+2, cellSize-4, cellSize-4);
    }
    checkSuccess(matrice);
}
export function checkSuccess(matrice) {
    console.log(matrice);

    if (matrice == gameMatrice) {
        console.log("victoire !!!!!!");
    }
}
export function calculeClues(list) {
    let res = [];
    let count = 0;
    const listLength = list.length;
    for (let i = 0; i < listLength; i++) {
        if(list[i] == 1) {
            count++;
            if(i == listLength - 1) {
                res.push(count);
            }
        } else {
            if (i > 0) {
                if(count > 0) {
                    res.push(count);
                }
                count = 0;
            }
        }
    }
    return res;
}
export function createPuzzle() {
    // vide la matrice, si l'on créé une nouvelle grille
    newMatrice.length = 0;
    // récupère la valeur de 'largeur'
    const sizeX = document.getElementById("sizeX");
    // récupère la valeur de 'hauteur'
    const sizeY = document.getElementById("sizeY");
    drawGrid(sizeX.value, sizeY.value);
    
    for (let i = 0; i < sizeY.value; i++) {
        const line = []
        newMatrice.push(line);
    }
    for (let i = 0; i < sizeX.value; i++) {
        for (let j = 0; j < sizeY.value; j++) {
            newMatrice[j].push(0);
        }   
    }

    const canvas = document.getElementById("picross");
    canvas.addEventListener("click", function (e) {
        getClick(newMatrice, canvas, e);
    });
    // if(!document.getElementById("exportPicross")) {
    //     const buttonExport = document.createElement("input");
    //     buttonExport.setAttribute("type", "submit");
    //     buttonExport.value = "Je valide";
    //     // buttonExport.addEventListener("click", function (e) {
    //     //     console.log("La matrice exportée est : "+JSON.stringify(newMatrice));
    //     // });
    //     document.getElementById("picrossBox").appendChild(buttonExport);
    // }
}