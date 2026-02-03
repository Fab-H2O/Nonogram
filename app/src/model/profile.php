<?php

namespace Application\Model\Profile;

require_once('src/lib/database.php');

use Application\Lib\Database\DatabaseConnection;

class ModelProfile
{
    public DatabaseConnection $connection;

    public function GetOwnInfos($id)
    {
        $infos = [];

        $req = $this->connection->get_connection()->prepare(
            'SELECT player_name, lastLog FROM player WHERE id LIKE :id'
        );
        $req->execute([
            'id' => $id
        ]);
        $res = $req->fetch();

        $infos['username'] = $res['player_name'];
        $infos['lastLog'] = $res['lastLog'];

        $req = $this->connection->get_connection()->prepare(
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
    public function GetOtherInfos($id)
    {
        $infos = [];

        $req = $this->connection->get_connection()->prepare(
            'SELECT player_name FROM player WHERE id LIKE :id'
        );
        $req->execute([
            'id' => $id
        ]);
        $res = $req->fetch();
        $infos['username'] = $res['player_name'];

        $req = $this->connection->get_connection()->prepare(
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