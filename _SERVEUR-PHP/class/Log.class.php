<?php
if (!defined('DIR')) exit('No direct script access allowed');

require_once('Ban.class.php');
require_once('UserManager.class.php');

class Log {

	private function __construct(){
	
	}
	
	public static function check_login($log, $pass){
		global $user, $data;
		$login = htmlentities($log);
		if (Ban::canLog($login)){
			$pass = htmlentities($pass);
			$passwd = sha1($pass);
			$tmp_user = UserManager::getUser(array($login, $passwd));

			if(empty($tmp_user)){ 
				$data['error'] = 'auth_error'; // login/pass incorrect
				Ban::add_log_try($login);
			}
			else {
				//utilisateur correct auth_ok
				$user = $tmp_user;
				self::generate_token();
			}
		}
		else{
			$data['error'] = 'auth_ban';
		}
	}

	public static function check_token($token){
		global $user, $data;
		// request POST (validation du form de log)
		$token = htmlentities($token);
		$tmp_user = UserManager::getUser($token);

		if(empty($tmp_user)){ 
			$data['error'] = 'token_error'; // login/pass incorrect
		}
		else {
			//utilisateur correct auth_ok
			$user = $tmp_user;
			$data["token"] = $user->getToken();
		}
	}


	public static function generate_token(){
		$dico = "123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ-_@$*";
		$token = '';
		for($i=0; $i<20; $i++){
			$random = rand(0, strlen($dico));
			$token .= substr($dico, $random, 1);
		}
		global $user, $data;
		$user->setToken($token);
		UserManager::updateUser($user);
		$data["token"] = $user->getToken();
	}
}
