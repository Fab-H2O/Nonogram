<?php 
    $title = "Picross - Accueil";
    $scripts = "<script src=\"src/js/homepage.js\" defer></script>";
    ob_start();
    require_once('header.php'); 
?>

<main>
    <div id="displayMessage">
        <?php echo $message; ?>
        <a href="./index.php">Retour à l'accueil</a>
    </div>
</main>

<?php 
    $content = ob_get_clean();
    require('layout.php');
?>