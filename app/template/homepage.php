<?php 
    $title = "Picross - Accueil";
    $scripts = "<script type=\"module\" src=\"src/js/homepage.js\"></script>";
    ob_start();
    require_once('header.php'); 
?>

<main>
    <H2>Ceci est la page d'accueil</H2>
</main>

<?php 
    $content = ob_get_clean();
    require('layout.php');
?>