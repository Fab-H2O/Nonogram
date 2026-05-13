<?php

namespace Application\Model\Homepage;

require_once('src/lib/database.php');

use Application\Lib\Database\DatabaseConnection;

class ModelHomepage
{
    public DatabaseConnection $connection;

    public function __construct(DatabaseConnection $connection)
    {
        $this->connection = $connection;
    }
    /* 
    GetUsername retourne le pseudo de l'utilisateur connecté 
    */
    public function getUsername(int $id): string
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
    public function getLastPuzzle(): array
    {
        $req = $this->connection->getConnection($_SESSION['dbUser'])->prepare(
            "SELECT matrice FROM puzzle ORDER BY id DESC LIMIT 1; "
        );
        $req->execute();
        $res = $req->fetch();

        return $res;
    }
    public function getAllPuzzles(): array 
    {
        $req = $this->connection->getConnection($_SESSION['dbUser'])->prepare(
            "SELECT * FROM puzzle"
        );
        $req->execute();
        $res = $req->fetchAll();
        
        $matrices = [];
        foreach ($res as $r) {
            $matrice = [];
            $matrice['matrice'] = $r['matrice'];
            $matrice['creator'] = $this->getUsername($r['creator']);
            $matrice['id'] = $r['id'];
            array_push($matrices, $matrice);
        }
        return $matrices;
    }
}