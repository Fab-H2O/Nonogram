<?php

namespace Application\Model\Profile;

require_once('src/lib/database.php');

use Application\Lib\Database\DatabaseConnection;

class ModelProfile
{
    public DatabaseConnection $connection;
    /*
    GetOwnInfos retourne les informations concernant l'utilisateur connecté.
    */
    public function GetOwnInfos($id)
    {
        $infos = [];
        // cherche le pseudo et la dernière date de connexion
        $req = $this->connection->getConnection($_SESSION['dbUser'])->prepare(
            'SELECT player_name, lastLog FROM player WHERE id LIKE :id'
        );
        $req->execute([
            'id' => $id
        ]);
        $res = $req->fetch();

        $infos['username'] = $res['player_name'];
        $infos['lastLog'] = $res['lastLog'];

        // cherche l'id des puzzles créés par l'utilisateur connecté et les met dans un tableau
        $req = $this->connection->getConnection($_SESSION['dbUser'])->prepare(
            'SELECT id FROM puzzle WHERE creator LIKE :id'
        );
        $req->execute([
            'id' => $id
        ]);
        $res = $req->fetchAll();
        $puzzles = [];
        foreach($res as $puzzle)
        {
            array_push($puzzles, $puzzle);
        }
        $infos['puzzles'] = $puzzles;

        return $infos;
    }
    /*
    GetOtherInfos retourne les informations d'un utilisateur selon son id 
    */
    public function GetOtherInfos($id)
    {
        $infos = [];

        // cherche le pseudo
        $req = $this->connection->getConnection($_SESSION['dbUser'])->prepare(
            'SELECT player_name FROM player WHERE id LIKE :id'
        );
        $req->execute([
            'id' => $id
        ]);
        $res = $req->fetch();
        $infos['username'] = $res['player_name'];

        // cherche les id des puzzles créés par l'utilisateur et les met dans un tableau
        $req = $this->connection->getConnection($_SESSION['dbUser'])->prepare(
            'SELECT id FROM puzzle WHERE creator LIKE :id'
        );
        $req->execute([
            'id' => $id
        ]);
        $res = $req->fetchAll();
        $puzzles = [];
        foreach($res as $puzzle)
        {
            array_push($puzzles, $puzzle);
        }
        $infos['puzzles'] = $puzzles;

        return $infos;
    }
}