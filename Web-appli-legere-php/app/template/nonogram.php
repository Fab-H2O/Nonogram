<?php 
    $title = "Picross - Accueil";
    $scripts = "<script type=\"module\" src=\"src/js/test.js\" defer></script>";
    ob_start();
    require_once('header.php'); 
?>

<main>
        
    </div>
    <h1 id="titre">Nonograms</h1>

    <!--grille et indice-->
    <div class="nono-carre">
        <div id="colonne"></div>
        <div id="ligne"></div>
        <div id="nono-stock"></div>
    </div>

    <!--bouton choix taille grille-->
    <div class="btn-choix">
        <button id="5">5 / 5</button>
        <button id="10">10 / 10</button>
        <button id="20">20 / 20</button>
        <button id="30">30 / 30</button>
        <button id="random">Random</button>
    </div>
</main>

<?php 
    $content = ob_get_clean();
    require('layout.php');
?>