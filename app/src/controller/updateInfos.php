<?php

namespace Application\Controller\UpdateInfos;

require_once('src/lib/database.php');
require_once('src/model/updateInfos.php');
require_once('src/model/log.php');

use Application\Lib\Database\DatabaseConnection;
use Application\Model\UpdateInfos\ModelUpdateInfos;
use Application\Model\Log\ModelLogIn;

class ControllerUpdateInfos
{
    public function execute(?array $input)
    {
        $connection = new DatabaseConnection();

        // vérifie que l'ancien mot de passe est le bon en utilisant l'objet 'logIn'

        $inputToCheck = [
            "username" => $_SESSION['username'],
            "password" => $input['oldPassword']
        ];
        $logIn = new ModelLogIn();
        $logIn->connection = $connection;

        if($logIn->LogIn($inputToCheck) == 0) // l'authentification échoue
        {
            $message = "Pseudo ou mot de passe éronné";
            require('template/displayMessage.php');
        }
        else // l'authentification réussit
        {
            $updateInfos = new ModelUpdateInfos();
            $updateInfos->connection = $connection;
            $res = $updateInfos->UpdateUserInfos($input);
            
            if($res == -1) 
            {
                $message = "Le pseudo n'est pas disponible";
                require('template/displayMessage.php');
            }
            else
            {
                if(!empty($input['username'])) // modifie le pseudo enregistré en session s'il y a une modification du pseudo    
                {
                    $_SESSION['username'] = $input['username'];
                }
                $message = "Informations modifiées !";
                require('template/displayMessage.php');
            }
        }
    }
}