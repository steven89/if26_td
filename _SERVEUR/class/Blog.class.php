<?php
if (!defined('DIR')) exit('No direct script access allowed');

class Blog{

	private function __construct(){
	
	}

	public static function post($title, $content){
		global $db, $user;
		
		$req = "INSERT INTO `blog` (`user_id`,`title`,`content`,`date`) VALUES(:user, :title, :content, :date) ;";
		$req = $db->prepare($req);
		$req->bindValue(':user', $user->getId(), PDO::PARAM_INT);
		$req->bindValue(':title', $title, PDO::PARAM_STR);
		$req->bindValue(':content', $content, PDO::PARAM_STR);
		$req->bindValue(':date', time(), PDO::PARAM_STR);
		$req->execute();
		$req->closeCursor();
	}

	public static function liste($limit){
		global $db;
		
		$req = "SELECT title, content, date, name as author FROM `blog` INNER JOIN `users` ON user_id = users.id ORDER BY `date` DESC LIMIT 0, :limit;";
		//$req = "SELECT title, content, date, name FROM `blog`,`users` WHERE user_id = users.id ORDER BY `date` DESC ;";
		$req = $db->prepare($req);
		$req->bindValue(':limit', $limit, PDO::PARAM_INT);
		$req->execute();
		$liste = $req->fetchAll(PDO::FETCH_ASSOC);
		$req->closeCursor();
		
		foreach($liste as $i=>$article){
			$liste[$i]['date'] = date('d/m/Y - H:i', $article['date']);
		}
		
		return $liste;
	}

}