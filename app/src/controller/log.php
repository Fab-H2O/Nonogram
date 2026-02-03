<?php

namespace Application\Controller\Log;

require_once('src/lib/database.php');
require_once('src/model/log.php');

use Application\Lib\Database\DatabaseConnection;
use Application\Model\Log\ModelLogIn;
use Application\Model\Log\ModelLogOut;

class ControllerLogIn
{
    public function execute(?array $input)
    {
        $connection = new DatabaseConnection;
        $logIn = new ModelLogIn;
        $logIn->connection = $connection;
        $idUser = $logIn->LogIn($input);
        
        if($idUser > 0)
        {
            $_SESSION['idUserLogged'] = $idUser;
            $url = "././index.php";
            header( "Location: $url" );
        }
        else
        {
            $errorMessage = "Pseudo ou mot de passe éronné";
            require('template/error.php');
        } 
    }
}
class ControllerLogOut
{
    public function execute()
    {
        session_unset();

        $url = "././index.php";
        header( "Location: $url" );
    }
}