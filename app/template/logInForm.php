<form action="index.php?action=logIn" method="post" class="form" id="logInForm">  
    <button type="button" onclick="closeElement('#logInForm')" class="closeButton">✖</button>  
    <label for="username_logInForm">Votre pseudo : </label> 
    <input type="text" name="username" id="username_logInForm" autocomplete="off">
    <label for="password_logInForm">Votre mot de passe : </label> 
    <input type="password" name="password" id="password_logInForm" autocomplete="off">
    <input type="submit" value="Valider">
</form>