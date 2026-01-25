<?php

namespace Application\Model\Sign;

require_once('src/lib/database.php');

use Application\Lib\Database\DatabaseConnection;

class ModelSignIn
{
    public DatabaseConnection $connection;
    
    public function CreateNewUser(array $input): int
    {
        //vérifie si le pseudo existe déja dans la base de donnée   
        $req = $this->connection->get_connection()->prepare(
            "SELECT count(*) FROM player WHERE player_name = :username"
        );
        $req->execute([
            'username' => $input['username']
        ]);
        $res = $req->fetch();
        if($res['count(*)'] == "1")
        {
            return -1;
        }
        else
        {
            // crée une entrée 'player'
            $req = $this->connection->get_connection()->prepare(
                "INSERT INTO player (player_name, password, isAdmin, lastLog) VALUES (:username, :password, false, NOW())"
            );
            $req->execute([
                'username' => $input['username'],
                'password' => password_hash($input['password'], PASSWORD_DEFAULT)
            ]);

            return $this->connection->get_connection()->lastInsertId();
        }
    }
}