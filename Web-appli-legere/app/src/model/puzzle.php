<?php

namespace Application\Model\Puzzle;

require_once('src/lib/database.php');

use Application\Lib\Database\DatabaseConnection;


class ModelAddPuzzle
{
    public DatabaseConnection $connection;
    
    public function __construct(DatabaseConnection $connection)
    {
        $this->connection = $connection;
    }

    public function addPuzzle($input): string
    {
        try{
            $req = $this->connection->getConnection($_SESSION['dbUser'])->prepare(
                "INSERT INTO puzzle (matrice, creator) VALUES (:matrice, :creator)"
            );
            $req->execute([
                "matrice" => $input['matrice'],
                "creator" => $input['idUser']
            ]);
            return "Nouveau puzzle ajouté avec succès";
        } catch (Exception $e){
            return $e->getMessage();
        }
    }
}
class ModelRemovePuzzle
{
    public DatabaseConnection $connection;

    public function __construct(DatabaseConnection $connection)
    {
        $this->connection = $connection;
    }

    public function removePuzzle($input): String
    {
        try {        
            
            $req = $this->connection->getConnection($_SESSION['dbUser'])->prepare(
                "DELETE FROM puzzle WHERE id = :id"
            );
            $req->execute([
                "id" => $input["id"]
            ]);

            return "Puzzle supprimé";
        
        } catch (Exception $e) {
            return "Une erreur est survenue";
        }
    }
}
class ModelGetPuzzle
{
    public DatabaseConnection $connection;
    
    public function __construct(DatabaseConnection $connection)
    {
        $this->connection = $connection;
    }
    
    public function getPuzzle($input)
    {
        $req = $this->connection->getConnection($_SESSION['dbUser'])->prepare(
            "SELECT matrice from puzzle WHERE id = :id"
        );
        $req->execute([
            "id" => $input["id"]
        ]);
        $res = $req->fetch();
    }
}