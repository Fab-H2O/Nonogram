<?php

namespace Application\Model\Sign;

require_once('src/lib/database.php');

use Application\Lib\Database\DatabaseConnection;

class ModelSign
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
        if($res['count(*)'] == "1") // si oui : retourne erreur ('-1')
        {
            return -1;
        }
        else
        {
            // crée une entrée 'player'
            $req = $this->connection->get_connection()->prepare(
                "INSERT INTO player (player_name, player_pwd, isAdmin, lastLog, sign_out) VALUES (:username, :pwd, false, NOW(), 0)"
            );
            $req->execute([
                'username' => $input['username'],
                'pwd' => password_hash($input['password'], PASSWORD_DEFAULT)
            ]);
            $res = $this->connection->get_connection()->lastInsertId();
            return $res;
        }
    }
    public function SignOut(int $id)
    {
        $req = $this->connection->get_connection()->prepare(
            "UPDATE player SET sign_out = 1 WHERE id = :id;"
        );
        $req->execute([
            'id' => $id
        ]);
    }
}