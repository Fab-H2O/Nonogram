<?php 
    $username = $infos['username'];
    $title = "Picross - " . $username;
    $scripts = "<script type=\"module\" src=\"src/js/profile.js\" defer></script>";
    ob_start();
    require_once('header.php'); 
?>
<main>
    <?= require('displayUserInfos.php'); ?>
    <button type="button" id="displayUpdateForm">Modifier mes infos</button>
    <?= require('updateInfosForm.php'); ?>
    <button type="button" id="displayAddPuzzleForm">Créer un puzzle</button>
    <?= require('addPuzzleForm.php'); ?>
</main>
<?php 
    $content = ob_get_clean();
    require('layout.php');
?>