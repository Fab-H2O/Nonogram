<form action="index.php?action=puzzle" method="post" class="form" id="addPuzzleForm"> 
    <div class="boxHeadLine">    
        <p>Créer un puzzle</p> 
        <button type="button" class="closeButton" id="closeAddPuzzleForm">✖</button>
    </div>
    <div class="formFields">
        <input type="hidden" name="idUser" value="<?= $_SESSION['idUserLogged'] ?>">

        <label for="matrice_addPuzzleForm">Contiendra la matrice envoyée à la base de donnée</label>
        <input type="text" name="matrice" id="matrice_addPuzzleForm"  autocomplete="off">
        
        <input type="submit" value="Valider">
    </div>
</form>