<?php

namespace Application\Model\Homepage;

require_once('src/lib/database.php');

use Application\Lib\Database\DatabaseConnection;

class ModelHomepage
{
    public DatabaseConnection $connection;

    /* 
    GetUsername retourne le pseudo de l'utilisateur connecté 
    */
    public function GetUsername(int $id): string
    {
        $req = $this->connection->getConnection($_SESSION['dbUser'])->prepare(
            "SELECT player_name FROM player WHERE player.id = :id"
        );
        $req->execute([
            'id' => $id
        ]);
        $res = $req->fetch();
        
        return $res['player_name'];
    }
}