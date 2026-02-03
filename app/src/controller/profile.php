<?php

namespace Application\Controller\Profile;

require_once('src/lib/database.php');
require_once('src/model/profile.php');

use Application\Lib\Database\DatabaseConnection;
use Application\Model\Profile\ModelProfile;

class ControllerProfile
{
    public function execute($input)
    {
        $connection = new DatabaseConnection();
        if(isset($_SESSION['idUserLogged']) && $input['idUser'] == $_SESSION['idUserLogged']) 
        {
            $profile = new ModelProfile();
            $profile->connection = $connection;
            $infos = $profile->GetOwnInfos($input['idUser']);
            require('template/profileOwn.php');
        }
        else
        {
            $profile = new ModelProfile();
            $profile->connection = $connection;
            $infos = $profile->GetOtherInfos($input['idUser']);
            require('template/profileOther.php');
        }
    }
}