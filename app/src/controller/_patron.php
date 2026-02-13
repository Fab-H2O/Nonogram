<?php
/*
template des classes de type controller
*/
namespace Application\Controller\;

require_once('src/lib/database.php');
require_once('src/model/.php');

use Application\Lib\Database\DatabaseConnection;
use Application\Model\\Model;

class Controller
{
    public function execute(?array $input)
    {
        $connection = new DatabaseConnection();
        $ = new ();
        $ ->connection = $connection;
    }
}