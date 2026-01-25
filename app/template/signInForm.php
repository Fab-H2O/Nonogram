<form action="index.php?action=signIn" method="post" class="form" id="signInForm">  
    <button type="button" onclick="closeElement('#signInForm')" class="closeButton">✖</button>  
    <label for="username_signInForm">Choisissez un pseudo : </label> 
    <input type="text" name="username" id="username_signInForm" autocomplete="off">
    <label for="password_signInForm">Choisissez un mot de passe : </label> 
    <input type="password" name="password" id="password_signInForm" autocomplete="off">
    <input type="submit" value="Valider">
</form>