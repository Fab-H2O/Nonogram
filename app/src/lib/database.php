<?php

namespace Application\Lib\Database;

class DatabaseConnection
{
    public ?\PDO $database = null;

    public function get_connection(): \PDO
    {
        $host = 'localhost';
        $db = 'nonogram';
        $user = 'root';
        $pass = 'root';
        $charset = 'utf8mb4';

        $dsn = "mysql:host=$host;dbname=$db;charset=$charset";
        
        if ($this->database == null) {
            $this->database = new \PDO($dsn, $user, $pass);
        }

        return $this->database;
    }
}