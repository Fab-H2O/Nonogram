<?php

namespace Application\Controller\Homepage;

require_once('src/lib/database.php');
require_once('src/model/homepage.php');

use Application\Lib\Database\DatabaseConnection;
use Application\Model\Homepage\ModelHomepage;

class ControllerHomepage
{
    public function execute()
    {
        $connection = new DatabaseConnection();
        $homepage = new ModelHomepage();
        $homepage->connection = $connection;

        if(isset($_SESSION['idUserLogged']))
        {
            $id = $_SESSION['idUserLogged'];
            echo("<script>console.log($id);</script>");
            if(!isset($_SESSION['username']) && $_SESSION['idUserLogged'] > 0)
            {    
                $_SESSION['username'] = $homepage->GetUsername($_SESSION['idUserLogged']);
            } 
        }
        require('template/homepage.php');
    }
}