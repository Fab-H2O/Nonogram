<?php

namespace Application\Controller\Sign;

require_once('src/lib/database.php');
require_once('src/model/sign.php');

use Application\Lib\Database\DatabaseConnection;
use Application\Model\Sign\ModelSignIn;

class ControllerSignIn
{
    public function execute(?array $input)
    {
        $connection = new DatabaseConnection;
        $signIn = new ModelSignIn;
        $signIn->connection = $connection;
        
        if($signIn->CreateNewUser($input) < 0)
        {
            $errorMessage = "Pseudo non disponible";
            require('template/error.php');
        }
        else
        {
            $_SESSION['idUserLogged'] = $signIn->CreateNewUser($input);
            $_SESSION['username'] = $input['username'];
            $url = "././index.php";
            header( "Location: $url" );
        }
    }
}
class ControllerSignOut
{
    public function execute()
    {
        
    }
}