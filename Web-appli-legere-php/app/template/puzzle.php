<?php 
    $title = "Picross - Puzzle";
    $scripts = "<script type=\"module\" src=\"src/js/profile.js\" defer></script>";
    ob_start();
    require_once('header.php'); 
?>
<main>
    <div class="displayPuzzle">
        <?= echo $puzzle; ?>
    </div>
</main>
<?php 
    $content = ob_get_clean();
    require('layout.php');
?>