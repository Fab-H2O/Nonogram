<?php 
    $title = "Picross - Accueil";
    $scripts = "<script type=\"module\" src=\"src/js/homepage.js\" defer></script>";
    ob_start();
    require_once('header.php'); 
?>

<main>
    <button id="playPuzzle">Jouer un puzzle</button>
    <div id="puzzlesList"></div>
    <div id="picrossBox" class="centered" style="display : none">
        
    </div>
</main>

<?php 
    $content = ob_get_clean();
    require('layout.php');
?>