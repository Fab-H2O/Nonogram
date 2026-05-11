<?php

namespace Application\Lib\Database;

class DatabaseConnection
{
    public ?\PDO $database = null;
    /*
    get_connection permet de se connecter à la base de données selon 
    */
    public function getConnection(String $dbUser): \PDO
    {
        $host = 'localhost';
        $db = 'nonogram';
        $pass = "";
        $user = $dbUser;
        
        if ($user == 'root') 
        {
            $pass = 'root';
        }

        $charset = 'utf8mb4';

        $dsn = "mysql:host=$host;dbname=$db;charset=$charset";
        
        if ($this->database == null) {
            $this->database = new \PDO($dsn, $user, $pass);
        }

        return $this->database;
    }
}