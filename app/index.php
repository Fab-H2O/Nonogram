<?php

session_start();

require_once('src/controller/homepage.php');
require_once('src/controller/sign.php');
require_once('src/controller/log.php');

use Application\Controller\Homepage\ControllerHomepage;
use Application\Controller\Sign\ControllerSignIn;
use Application\Controller\Sign\ControllerSignOut;
use Application\Controller\Log\ControllerLogIn;
use Application\Controller\Log\ControllerLogOut;

try {
    if (isset($_GET['action']) && $_GET['action'] !== '') 
    {
        if ($_GET['action'] === 'signIn') 
        {
            // remplis le $input seulement si la méthode post a été utilisée (donc que le formulaire a été correctement rempli)
            $input = null;
            if ($_SERVER['REQUEST_METHOD'] === 'POST') {
                $input = $_POST;
            }
            (new ControllerSignIn())->execute($input);
        }
        else if ($_GET['action'] === 'logIn') 
        {
            // remplis le $input seulement si la méthode post a été utilisée (donc que le formulaire a été correctement rempli)
            $input = null;
            if ($_SERVER['REQUEST_METHOD'] === 'POST') {
                $input = $_POST;
            }
            (new ControllerLogIn())->execute($input);
        }
        else if ($_GET['action'] === 'logOut') 
        {
            (new ControllerLogOut())->execute();
        }
        else 
        {
            throw new Exception("La page que vous recherchez n'existe pas.");
        }
    } 
    else 
    {
        (new ControllerHomepage())->execute();
    }
} catch (Exception $e) {
    $errorMessage = $e->getMessage();

    require('template/error.php');
}