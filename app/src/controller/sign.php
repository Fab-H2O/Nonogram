<?php

namespace Application\Controller\Sign;

require_once('src/lib/database.php');
require_once('src/model/sign.php');

use Application\Lib\Database\DatabaseConnection;
use Application\Model\Sign\ModelSign;

class ControllerSignIn
{
    public function execute(?array $input)
    {
        $connection = new DatabaseConnection();
        $sign = new ModelSign();
        $sign->connection = $connection;
        
        $id = $sign->CreateNewUser($input);

        if($id < 0)
        {
            $message = "Pseudo non disponible";
            require('template/displayMessage.php');
        }
        else
        {
            $_SESSION['idUserLogged'] = $id;
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
        $connection = new DatabaseConnection();
        $sign = new ModelSign();
        $sign->connection = $connection;

        $sign->SignOut($_SESSION['idUserLogged']);
        
        $url = "././index.php";
        header( "Location: $url" );
    }
}