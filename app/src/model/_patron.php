<?php
/*
template des classes de type modèle
*/
namespace Application\Model\;

require_once('src/lib/database.php');

use Application\Lib\Database\DatabaseConnection;

class Model
{
    public DatabaseConnection $connection;
    
    public function (): 
    {
        $req = $this->connection->get_connection()->prepare(
            ""
        );
        $req->execute([
            
        ]);
        $res = $req->fetch();
    }
}