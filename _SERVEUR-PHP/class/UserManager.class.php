<?php
if (!defined('DIR')) exit('No direct script access allowed');
/**
* Class de gestion des utilisateurs en DB
* Savegarde, recuperation, suppression d'utilisateurs
*/
require_once('User.class.php');
class UserManager{
	
	private static $_db;
	
	public function __construct(&$db){
		self::setDb($db);
	}
	
	public static function setDb(PDO &$db){
		self::$_db = $db;
	}
	
	/*public static function createUser(User $user){
		if(!self::exists($user->getEmail_user())){
			$req="INSERT INTO `users`(`email_user`, `pass_user`, `nom_user`, `prenom_user`, `type_user`, `tel_user`, `adresse_user`)
					VALUES(:email, :pass, :nom, :prenom, :type, :tel, :adr)";
			$req=self::$_db->prepare($req);
			$req->bindValue(':email', utf8_decode($user->getEmail_user()), PDO::PARAM_STR);
			$req->bindValue(':pass', $user->getPass_user(), PDO::PARAM_STR);
			$req->bindValue(':nom', utf8_decode($user->getNom_user()), PDO::PARAM_STR);
			$req->bindValue(':prenom', utf8_decode($user->getPrenom_user()), PDO::PARAM_STR);
			$req->bindValue(':type', $user->getType_user(), PDO::PARAM_STR);
			$req->bindValue(':tel', $user->getTel_user(), PDO::PARAM_INT);
			$req->bindValue(':adr', utf8_decode($user->getAdresse_user()), PDO::PARAM_STR);
			$req->execute();
			$req->closeCursor();
			return 'add_ok';
		}
		else {
			return 'exists';
		}
	}
	
	
	
	public static function delUser(User $user){
		if(self::exists($user->getId_user())){
			$req = "DELETE FROM `users` WHERE `id_user`=:id";
			$req = $req = self::$_db->prepare($req);
			$req->bindValue(':id', $user->getId_user(), PDO::PARAM_INT);
			$req->execute();
			$req->closeCursor();
			return 'del_ok';
		}
	}*/
	public static function updateUser(User $user){
		if(self::exists($user->getId())){	
				$req = "UPDATE `users` SET `name`=:name, `passwd`= :pass, `token`=:token
				WHERE `id`=:id";
				$req=self::$_db->prepare($req);
				$req->bindValue(':id', $user->getId(), PDO::PARAM_INT);
				$req->bindValue(':name', utf8_decode($user->getName()), PDO::PARAM_STR);
				$req->bindValue(':pass', $user->getPasswd(), PDO::PARAM_STR);
				$req->bindValue(':token', $user->getToken(), PDO::PARAM_STR);
				$req->execute();
				$req->closeCursor();
				return 'alter_ok';
		}
	}
	public static function getUser($data){
		if(self::exists($data)){
			if(is_numeric($data)){
				$req = "SELECT *
						FROM `users` 
						WHERE `id`=:id";
				$req = self::$_db->prepare($req);
				$req->bindValue(':id', $data, PDO::PARAM_INT);
				$req->execute();
				$user = $req->fetch(PDO::FETCH_ASSOC);
				$req->closeCursor();
				return new User($user);
				
			}
			elseif(is_string($data)){
				$req = "SELECT *
						FROM `users` 
						WHERE `token`=:token";
				$req = self::$_db->prepare($req);
				$req->bindValue(':token', utf8_decode($data), PDO::PARAM_INT);
				$req->execute();
				$user = $req->fetch(PDO::FETCH_ASSOC);
				$req->closeCursor();
				return new User($user);
			}
			elseif(is_array($data)) {
				$login = $data[0];
				$passwd = $data[1];
				$req = "SELECT *
						FROM `users` 
						WHERE `name`=:login AND `passwd`=:pass";
				$req = self::$_db->prepare($req);
				$req->bindValue(':login', utf8_decode($login), PDO::PARAM_STR);
				$req->bindValue(':pass', $passwd, PDO::PARAM_STR);
				$req->execute();
				$user = $req->fetch(PDO::FETCH_ASSOC);
				$req->closeCursor();
				return new User($user);
			}
		}
		else return null;
	}
	
	public static function exists($data){
		$req="";
		if(is_numeric($data)){ //id
			$req = "SELECT `id`
					FROM `users` 
					WHERE `id`=:id";
			$req = self::$_db->prepare($req);
			$req->bindValue(':id', $data, PDO::PARAM_INT);
		}
		elseif(is_string($data)){ // email
			$req = "SELECT `id`
					FROM `users` 
					WHERE `token`=:token";
			$req = self::$_db->prepare($req);
			$req->bindValue(':token', $data, PDO::PARAM_STR);
		}
		elseif(is_array($data)){
			$login = $data[0];
			$passwd = $data[1];
			$req = "SELECT `id`
					FROM `users` 
					WHERE `name`=:login AND `passwd`=:pass";
			$req = self::$_db->prepare($req);
			$req->bindValue(':login', utf8_decode($login), PDO::PARAM_STR);
			$req->bindValue(':pass', $passwd, PDO::PARAM_STR);
		}
		else{
			return false;
		}
		$req->execute();
		$user = $req->fetch(PDO::FETCH_ASSOC);
		$req->closeCursor();
		return (!empty($user))?true:false;
	}
}
