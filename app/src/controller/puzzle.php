<?php

namespace Application\Controller\Puzzle;

require_once('src/lib/database.php');
require_once('src/model/puzzle.php');

use Application\Lib\Database\DatabaseConnection;
use Application\Model\Puzzle\ModelAddPuzzle;
use Application\Model\Puzzle\ModelRemovePuzzle;
use Application\Model\Puzzle\ModelGetPuzzle;

class ControllerPuzzle
{
    /*
    $input contient une variable String 'action' et une variable int 'id' ou une variable String 'matrice'
    */
    public function execute(?array $input)
    {
        $connection = new DatabaseConnection();
        if($input['action'] == 'add') 
        {
            $add = new ModelAddPuzzle();
            $add->connection = $connection;
            // aller vers la page du puzzle
        } 
        else if ($input['action'] == 'remove')
        {
            $remove = new ModelRemovePuzzle();
            $remove->connection = $connection;
            $message = $remove->removePuzzle($input);
            require('template/displayMessage.php');
        }
        else if($input['action'] == 'get')
        {
            $get = new ModelGetPuzzle();
            $get->connection = $connection;
            $puzzle = $get->getPuzzle($input);
            require('template/puzzle.php');
        }
    }
}