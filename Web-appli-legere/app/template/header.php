<header>
    <a href="index.php">
        <H1>
            Super Picross
        </H1>
    </a>
<div class="headerbox">
<?php
    if(isset($_SESSION['idUserLogged']))
    {
        $id = $_SESSION['idUserLogged'];
        echo("<p>bonjour ".$_SESSION["username"]."</p>");
        echo("
                <form action=\"index.php?action=profile\" method=\"post\">
                    <input type=\"hidden\" name=\"idUser\" value=\"$id\">
                    <input type=\"submit\" value =\"Mon profil\"/>
                </form>
        ");
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
        echo("<button type=\"button\" id=\"displaySignInForm\">S'inscrire</button>");
        echo("<button type=\"button\" id=\"displayLogInForm\">Se connecter</button>");
    }
?>
</div>
</header>