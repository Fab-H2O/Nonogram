<form action="index.php?action=puzzle" method="post" class="form" id="addPuzzleForm"> 
    <div class="boxHeadLine">    
        <p>Créer un puzzle</p> 
        <button type="button" class="closeButton" id="closeAddPuzzleForm">✖</button>
    </div>
    <div class="formFields">
        <input type="hidden" name="action" value="add">
        <input type="hidden" name="idUser" value="<?= $_SESSION['idUserLogged'] ?>">
        <input type="hidden" name="matrice" id="matrice" value="">

        <label for="sizeX">Largeur</label>
        <input type="number" name="sizeX" id="sizeX" min="2" max="30" value="10">
    
        <label for="sizeY">Hauteur</label>
        <input type="number" name="sizeY" id="sizeY" min="2" max="30" value="10">
    
        <button type="button" id="createPicross">Créer un nouvelle grille</button>
        
        <div id="picrossBox" class="centered">
            <button type="button" class="closeButton" id="closePicrossBox">✖</button>
            <div id="boxTop"></div>
            <div id="boxBottom"></div>
            <input type="submit" value="Valider">
        </div>
    </div>
</form>