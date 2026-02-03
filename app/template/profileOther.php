<?php 
    $username = $infos['username'];
    $title = "Picross - " . $username;
    $scripts = "<script type=\"module\" src=\"src/js/profile.js\" defer></script>";
    ob_start();
    require_once('header.php'); 
?>

<main>
    <?= require('displayUserInfos.php'); ?>
</main>

<?php 
    $content = ob_get_clean();
    require('layout.php');
?>