<?php

namespace Application\Model\UpdateInfos;

require_once('src/lib/database.php');

use Application\Lib\Database\DatabaseConnection;


class ModelUpdateInfos
{
    public DatabaseConnection $connection;
    
    public function UpdateUserInfos(array $input): int
    {
        if($input['username'] != $_SESSION['username']) // si le nouveau pseudo est différent de l'actuel : vérifie si le nouveau pseudo existe déja dans la base de donnée
        {   
            $req = $this->connection->get_connection()->prepare(
                "SELECT count(*) FROM player WHERE player_name = :username"
            );
            $req->execute([
                'username' => $input['username']
            ]);
            
            $res = $req->fetch();
            
            if($res['count(*)'] == "1") // si oui : retourne une erreur
            {
                return -1;
            }
            else 
            {
                $req = $this->connection->get_connection()->prepare(
                    "UPDATE player SET player_name = :new_name WHERE id = :id;"
                );
                $req->execute([
                    'new_name' => $input['username'],
                    'id' => $_SESSION['idUserLogged']
                ]);
            }
        }

        // modifie le password si le champ a été complété dans le formulaire
        if(!empty($input['newPassword']))
        {
            $newPassword = password_hash($input['newPassword'], PASSWORD_DEFAULT);
            $req = $this->connection->get_connection()->prepare(
                "UPDATE player SET player_pwd = :new_password WHERE id = :id;"
            );
            $req->execute([
                'new_password' => $newPassword,
                'id' => $_SESSION['idUserLogged']
            ]);
        }
        return 0;
    }
}