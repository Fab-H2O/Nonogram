<form action="index.php?action=logIn" method="post" class="form" id="logInForm"> 
    <div class="boxHeadLine">
        <p>Connexion</p>
        <button type="button" class="closeButton" id="closeLogInForm">✖</button>
    </div>
    <div class="formFields">
        <label for="username_logInForm">Votre pseudo : </label> 
        <input type="text" name="username" id="username_logInForm" autocomplete="off">
        <label for="password_logInForm">Votre mot de passe : </label> 
        <input type="password" name="password" id="password_logInForm" autocomplete="off">
        <input type="submit" value="Valider">
    </div>
</form>