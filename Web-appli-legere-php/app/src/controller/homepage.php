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
        
        $homepage = new ModelHomepage($connection);

        if(isset($_SESSION['idUserLogged']))
        {
            if(!isset($_SESSION['username']) && $_SESSION['idUserLogged'] > 0)
            {    
                $_SESSION['username'] = $homepage->getUsername($_SESSION['idUserLogged']);
            } 
        }
        if(!isset($_SESSION['dbName'])) {
            $_SESSION['dbName'] = 'root';
        }
        
        $matrices = $homepage->getAllPuzzles();
        $json = json_encode($matrices);
        //echo "<script>const matrices = $json;</script>";//
        $matricesScript = "<script>const matrices = $json;</script>";


        require('template/homepage.php');
    }
}