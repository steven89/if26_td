<?php
if (!defined('DIR')) exit('No direct script access allowed');

class Ban {

	const MAX_LOG=10;
	
	public function __construct(){
		
	}
	
	public static function canLog($log){
		
		if(self::isBan($log)){
			return false;
		}
		else if(self::getLogTry($log) > self::MAX_LOG){
			self::ban($log);
			return false;
		}
		else 
			return true;
	}

	public static function ban($log){
		global $db;
		$req = "INSERT INTO `ban` (`log`, `date`) VALUES (:log, :date);";
		$req = $db->prepare($req);
		$req->bindValue(':log', $log, PDO::PARAM_STR);
		$req->bindValue(':date', time()+10*60, PDO::PARAM_STR);
		$req->execute();
		$req->closeCursor();
		self::clear_log_try($log);
	}

	public static function isBan($log){
		global $db;
		$req = "SELECT * FROM `ban` WHERE `log` = :log ORDER BY date DESC LIMIT 0,1 ;";
		$req = $db->prepare($req);
		$req->bindValue(':log', $log, PDO::PARAM_STR);
		$req->execute();
		$ban = $req->fetch(PDO::FETCH_ASSOC);
		$req->closeCursor();
		if( !empty($ban) && $ban['date'] > time()){
			return true;
		}
		else
			return  false;
	}

	public static function add_log_try($log){
		global $db;
		$req="INSERT INTO `tries`(`log`, `date`)
				VALUES(:log, :date)";
		$req=$db->prepare($req);
		$req->bindValue(':log', utf8_decode($log), PDO::PARAM_STR);
		$req->bindValue(':date', time(), PDO::PARAM_STR);
		$req->execute();
		$req->closeCursor();
	}

	public static function clear_log_try($log){
		global $db;
		$req = "DELETE FROM `tries` WHERE `log` = :log";
		$req=$db->prepare($req);
		$req->bindValue(':log', utf8_decode($log), PDO::PARAM_STR);
		$req->execute();
		$req->closeCursor();
	}

	public static function getLogTry($log){
		global $db;
		$req = "SELECT COUNT(`id`) FROM `tries` WHERE `log` = :log";
		$req=$db->prepare($req);
		$req->bindValue(':log', utf8_decode($log), PDO::PARAM_STR);
		$req->execute();
		$tries = $req->fetch();
		$req->closeCursor();
		return $tries[0];
	}
}
