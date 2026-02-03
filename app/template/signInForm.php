<form action="index.php?action=signIn" method="post" class="form" id="signInForm"> 
    <div class="boxHeadLine">    
        <p>Inscription</p> 
        <button type="button" class="closeButton" id="closeSignInForm">✖</button> 
    </div>
    <div class="formFields">
        <label for="username_signInForm">Choisissez un pseudo : </label> 
        <input type="text" name="username" id="username_signInForm" autocomplete="off">
        <label for="password_signInForm">Choisissez un mot de passe : </label> 
        <input type="password" name="password" id="password_signInForm" autocomplete="off">
        <input type="submit" value="Valider">
    </div>
</form>