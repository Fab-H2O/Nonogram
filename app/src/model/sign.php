<?php

namespace Application\Model\Sign;

require_once('src/lib/database.php');

use Application\Lib\Database\DatabaseConnection;

class ModelSign
{
    public DatabaseConnection $connection;
    
    public function __construct(DatabaseConnection $connection)
    {
        $this->connection = $connection;
    }
    /*
    CreateNewUser créé un nounelle entrée dans la table 'player' avec les données '$input' passées en paramètres
    */
    public function createNewUser(array $input): int
    {
        //vérifie si le pseudo existe déja dans la base de donnée   
        $req = $this->connection->getConnection($_SESSION['dbUser'])->prepare(
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
        else // si non : crée une entrée 'player'
        {
            $req = $this->connection->getConnection($_SESSION['dbUser'])->prepare(
                "INSERT INTO player (player_name, player_pwd, isAdmin, lastLog, sign_out) VALUES (:username, :pwd, false, NOW(), 0)"
            );
            $req->execute([
                'username' => $input['username'],
                'pwd' => password_hash($input['password'], PASSWORD_DEFAULT)
            ]);
            $res = $this->connection->getConnection($_SESSION['dbUser'])->lastInsertId();
            return $res;
        }
    }
    /*
    SignOut modifie la valeur 'sign_out' de l'entrée d'un player selon son id => cela signale que le player souhaite que l'admin
    supprime son compte.
    */
    public function signOut(int $id)
    {
        $req = $this->connection->getConnection($_SESSION['dbUser'])->prepare(
            "UPDATE player SET sign_out = 1 WHERE id = :id;"
        );
        $req->execute([
            'id' => $id
        ]);
    }
}