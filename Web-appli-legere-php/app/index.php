<?php

session_start();

require_once('src/controller/homepage.php');
require_once('src/controller/sign.php');
require_once('src/controller/log.php');
require_once('src/controller/profile.php');
require_once('src/controller/updateInfos.php');
require_once('src/controller/puzzle.php');

use Application\Controller\Homepage\ControllerHomepage;
use Application\Controller\Sign\ControllerSignIn;
use Application\Controller\Sign\ControllerSignOut;
use Application\Controller\Log\ControllerLogIn;
use Application\Controller\Log\ControllerLogOut;
use Application\Controller\Profile\ControllerProfile;
use Application\Controller\UpdateInfos\ControllerUpdateInfos;
use Application\Controller\Puzzle\ControllerPuzzle;

try {
    if(!isset($_SESSION['dbUser'])) {
        $_SESSION['dbUser'] = "root"; // a remplacer par l'user par défaut
    }
    if (isset($_GET['action']) && $_GET['action'] !== '') // vérifie si une action est demandée
    {
        if ($_GET['action'] === 'signIn') // action de s'inscrire
        {
            // remplis le $input seulement si la méthode post a été utilisée (donc que le formulaire a été correctement rempli)
            $input = null;
            if ($_SERVER['REQUEST_METHOD'] === 'POST') {
                $input = $_POST;
            }
            (new ControllerSignIn())->execute($input);
        }
        else if ($_GET['action'] === 'logIn') // action de se connecter
        {
            $input = null;
            if ($_SERVER['REQUEST_METHOD'] === 'POST') {
                $input = $_POST;
            }
            (new ControllerLogIn())->execute($input);
        }
        else if ($_GET['action'] === 'logOut') // action de se deconnecter
        {
            (new ControllerLogOut())->execute();
        }
        else if($_GET['action'] === 'profile') // action d'afficher un profil
        {
            $input = null;
            if ($_SERVER['REQUEST_METHOD'] === 'POST') {
                $input = $_POST;
            }
            (new ControllerProfile())->execute($input);
        }
        else if($_GET['action'] === 'updateInfos') // action de modifier ses informations (pseudo et mot de passe)
        {
            $input = null;
            if($_SERVER['REQUEST_METHOD'] === 'POST') {
                $input = $_POST;
            }
            (new ControllerUpdateInfos())->execute($input);
        }
        else if($_GET['action'] === 'puzzle')
        {
            $input = null;
            if($_SERVER['REQUEST_METHOD'] === 'POST') {
                $input = $_POST;
            }
            (new ControllerPuzzle())->execute($input);
        }
        else // il y a eu une erreur dans l'action demandée
        {
            throw new Exception("La page que vous recherchez n'existe pas.");
        }
    } 
    else // si pas d'action précisée, charge la page d'accueil
    {
        (new ControllerHomepage())->execute();
    }
} catch (Exception $e) { // si une erreur est levée, elle est affichée dans une vue dédiée
    $message = $e->getMessage();
    require('template/displayMessage.php');
}