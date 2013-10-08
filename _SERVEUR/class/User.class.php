<?php
if (!defined('DIR')) exit('No direct script access allowed');
/**
* Class User, represente un utilisateur
*/
class User{
	//string
	private $passwd='', 
			$name='',
			$token=''
	;
	//int
	private $id=0;
	
	public function __construct(array $data){
        $this->hydrate($data);
	}
	
	
	public function hydrate(array $data){
		foreach ($data as $key => $value){
			$method = 'set'.ucfirst($key);
			if (method_exists($this, $method)){
				$this->$method($value);
			}
		}
	}

	public function setId($val){
		$this->id = $val;
	}

	public function getId(){
		return $this->id;
	}
	
	public function setPasswd($val){
		$this->passwd = $val;
	}

	public function setToken($val){
		$this->token = $val;
	}
	
	public function setName($val){
		$this->name = utf8_encode($val);
	}

	public function getToken(){
		return $this->token;
	}
	
	public function getPasswd(){
		return $this->passwd;
	}
	
	public function getName(){
		return $this->name;
	}
}
