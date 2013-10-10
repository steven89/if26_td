<?php

define('DIR', true);
require_once('conf/sql_connect.php');
require_once('class/Utils.class.php');
global $db;

Utils::require_class('UserManager');
Utils::require_class('User');
Utils::require_class('Log');

UserManager::setDB($db);
$data = array();


if (!empty($_GET['log']) && !empty($_GET['pass']))
	Log::check_login($_GET['log'], $_GET['pass']);
else if(!empty($_GET['token']))
	Log::check_token($_GET['token']);
else
	$data['error'] = 'miss_value';

echo json_encode($data);
