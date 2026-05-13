<form action="index.php?action=updateInfos" method="post" class="form" id="updateInfosForm"> 
    <div class="boxHeadLine">    
        <p>Modifier mes informations</p> 
        <button type="button" class="closeButton" id="closeUpdateForm">✖</button>
    </div>
    <div class="formFields">
        <input type="hidden" name="idUser" value="<?= $_SESSION['idUserLogged'] ?>">

        <label for="username_updateInfosForm">Nouveau pseudo : </label>
        <input type="text" name="username" id="username_updateInfosForm" value ="<?= $_SESSION['username']?>" autocomplete="off">

        <label for="oldPassword_updateInfosForm">Ancien mot de passe : </label> 
        <input type="password" name="oldPassword" id="oldPassword_updateInfosForm" autocomplete="off">

        <label for="newPassword_updateInfosForm">Nouveau un mot de passe : </label> 
        <input type="password" name="newPassword" id="newPassword_updateInfosForm" autocomplete="off">
        
        <input type="submit" value="Valider">
    </div>
</form>