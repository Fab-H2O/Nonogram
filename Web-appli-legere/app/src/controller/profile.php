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
            $profile = new ModelProfile($connection);
            $infos = $profile->getOwnInfos($input['idUser']);
            require('template/profileOwn.php');
        }
        else
        {
            $profile = new ModelProfile($connection);
            $infos = $profile->getOtherInfos($input['idUser']);
            require('template/profileOther.php');
        }
    }
}