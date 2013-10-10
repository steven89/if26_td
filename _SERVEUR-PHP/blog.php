<?php

define('DIR', true);
require_once('conf/sql_connect.php');
require_once('class/Utils.class.php');
global $db;

Utils::require_class('UserManager');
Utils::require_class('Log');
Utils::require_class('Blog');

UserManager::setDB($db);
$data = array();

if(!empty($_GET['token'])){
	Log::check_token($_GET['token']); // log user into global $user
	
	if (isset($_GET['liste'])){
		$data['liste'] = Blog::liste(10);
	}
	else if(!empty($_POST['title']) && !empty($_POST['content'])){
		$title = $_POST['title'];
		$content = $_POST['content'];
		Blog::post($title, $content);
		$data['message'] = array($title, $content);
	}
	else {
		$data['error'] = 'no_action';
	}
}
else
	$data['error'] = 'auth_required';
	
echo json_encode($data);