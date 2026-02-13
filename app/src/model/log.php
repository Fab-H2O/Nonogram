<?php

namespace Application\Model\Log;

require_once('src/lib/database.php');

use Application\Lib\Database\DatabaseConnection;

class ModelLogIn
{
    public DatabaseConnection $connection;
    
    public function LogIn(array $input): int
    {
        $req = $this->connection->getConnection($_SESSION['dbUser'])->prepare(
            'SELECT id, player_pwd FROM player WHERE player_name LIKE :username '
        );
        $req->execute([
            'username' => $input['username']
        ]);
        
        //Vérifie que le pseudo existe : 
        $count = $req->rowCount();
        if($count == 1)
        {
            $res = $req->fetch();
        }
        else // si ce n'est pas le cas, renvoie 0
        {
            return 0;
        }
        
        // vérifie que le mot de passe correspond :
        if(password_verify($input['password'], $res['player_pwd']))
        {
            //met à jour la date de connexion    
            $req = $this->connection->getConnection($_SESSION['dbUser'])->prepare(
                'UPDATE player SET lastLog = NOW() WHERE id = :id'
            );
            $req->execute([
                'id' => $res['id']
            ]);
            // retourne l'id du player connecté
            return $res['id'];
        }
        else // si ce n'est pas le cas, renvoie 0
        {
            return 0;
        }
    }
}