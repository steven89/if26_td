<?php

/**
*
*/

class Db {
	
	private static $db;
	private static $config = array(
								'host' => 'localhost',
								'login' => 'root',
								'passwd' => '',
								'database' => 'if26'
							);
	
	public function __construct(){
		self::setDb();
	}
	
	public static function setDb(){
		try{
			self::$db = new PDO(
				"mysql:host=".self::config['host'].";dbname=".self::config['database'], 
				self::config['login'], 
				self::config['passwd']
			);
			return true;
		} catch(Exception $e){
			return false;
		}
	}
	
	/**
	* $datas format :
	* 	+table : string -- required
	*	+champs : string 'prenom, nom, ...' ; '*' ...
	*	-where : array associatif
	*		'champ' => 'value' 
	*		'champ' => ('value', 'inf|sup|inf_eq|sup_eq|like')
	*	-relation : string (and|or)
	*	-order : 
	*		string 'champs'
	*		array ('champs', 'ASC|DESC')
	*	-limit : array (offset, range)
	*/
	public static function select(array $datas){
		$datas = self::secureDatas($datas);
		$req = "SELECT $datas[champs] FROM `$datas[table]`";
		if ( !empty($datas['where']) ){
			$req .= ' WHERE ';
			$operator = (!empty($datas['relation'])) ? $datas['relation'] : 'and';
			foreach ($datas['where'] as $champ=>$value){
				req .= " `$champ` = :$champ $operator";
			}
			$req = substr($req, 0, -(strlen($operator)+1));
		}
		if ( !empty($datas['order']) ){
			if(is_array($datas['order']))
				$req .= " ORDER BY $datas[order][0] $datas[order][1]";
			else
				$req .= " ORDER BY $datas[order] ASC";
		}
		if ( !empty($datas['limit']) ){
			$req .= " LIMIT $datas[limit][0], $datas[limit][1]";
		}
		$req .= ' ;';
		$req = self::$db->prepare($req);
		foreach($datas['where'] as $champ=>$value){
			$req->bindValue(":$champ", $value);
		}
		$req->execute();
		$res = $req->fetch(PDO::FETCH_ASSOC);// TO CHECK
		$req->closeCursor();
	}
	
	/**
	* $datas format :
	* 	table : string
	*	where : array associatif 
	*		'champ' => 'value' 
	*		'champ' => ('value', 'inf|sup|inf_eq|sup_eq|like')
	*	relation : string (and|or)
	*/
	public static function delete(array $datas){
		
	}
	
	/**
	* $datas format :
	* 	table : string
	*	champs : array associatif
	*		'champ' => 'value'
	*	where : array associatif
	*		'champ' => 'value' 
	*		'champ' => ('value', 'inf|sup|inf_eq|sup_eq|like')
	*	relation : string (and|or)
	*/
	public static function update(array $datas){
		
	}
	
	
	/**
	* $datas format :
	* 	table : string
	*	champs : array associatif
	*		'champ' => 'value'
	*	where : array associatif
	*		'champ' => 'value' 
	*		'champ' => ('value', 'inf|sup|inf_eq|sup_eq|like')
	*	relation : string (and|or)
	*/
	public static function insert(array $datas){
		
	}
	
	private static function secureDatas(array $datas){
		return $datas;
	}
}
