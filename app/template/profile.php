<?php 
    $title = "Picross - Accueil";
    $scripts = "<script src=\"src/js/profile.js\" defer></script>";
    ob_start();
    require_once('header.php'); 
?>

<main>
    <H2>Ceci est la page de profil</H2>
</main>

<?php 
    $content = ob_get_clean();

    require('layout.php');
?>