<header>
    <a href="index.php">
        <H1>
            Super Picross
        </H1>
    </a>
<div class="headerbox">
<?php
    if(isset($_SESSION["username"]))
    {
        echo("bonjour ".$_SESSION["username"]);
        echo('
            <a href="index.php?action=logOut">
                <button type=\"button\">Se deconnecter</button>
            </a>
        ');
    }
    else
    {
        require_once('template/signInForm.php');
        require_once('template/logInForm.php');
        echo("<button type=\"button\" onclick=\"displayElement('#signInForm')\">S'inscrire</button>");
        echo("<button type=\"button\" onclick=\"displayElement('#logInForm')\">Se connecter</button>");
    }
?>
</div>
</header>